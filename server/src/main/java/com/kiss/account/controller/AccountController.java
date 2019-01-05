package com.kiss.account.controller;

import com.kiss.account.client.AccountClient;
import com.kiss.account.config.LdapConfig;
import com.kiss.account.entity.*;
import com.kiss.account.input.*;
import com.kiss.account.output.*;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.*;
import com.kiss.account.validator.AccountValidator;
import entity.Guest;
import exception.StatusException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.BeanCopyUtil;
import utils.ThreadLocalUtil;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Account", description = "账户相关接口")
public class AccountController extends BaseController implements AccountClient {


    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private LdapConfig ldapConfig;

    @Autowired
    private LangUtil langUtil;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @Override
    @ApiOperation(value = "添加账户")
    @Transactional
    public AccountOutput createAccount(@Validated @RequestBody CreateAccountInput createAccountInput) {

        Guest guest = ThreadLocalUtil.getGuest();

        Account account = new Account();
        BeanUtils.copyProperties(createAccountInput, account);

        String salt = CryptoUtil.salt();
        String password = LdapUtil.ssha(createAccountInput.getPassword(), salt);

        account.setStatus(1);
        account.setSalt(salt);
        account.setPassword(password);
        account.setName(createAccountInput.getName());
        account.setOperatorId(guest.getId());
        account.setOperatorIp(guest.getIp());
        account.setOperatorName(guest.getName());
        account.setType(2);
        accountDao.createAccount(account);

        if (ldapConfig.enabled) {
            AccountEntry accountEntry = new AccountEntry();
            accountEntry.setUid(account.getUsername());
            accountEntry.setName(account.getName());
            accountEntry.setUsername(account.getUsername());
            accountEntry.setPassword("{ssha}" + account.getPassword());
            accountEntry.setEmail(account.getEmail());
            accountEntry.setMobile(account.getMobile());
            accountEntryDao.save(accountEntry);
        }


        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account, accountOutput);

        AccountGroup group = accountGroupDao.getAccountGroupById(account.getGroupId());

        accountOutput.setGroupName(group.getName());
        accountOutput.setStatusText(langUtil.getEnumsMessage("accounts.status", String.valueOf(accountOutput.getStatus())));

        operationLogService.saveOperationLog(guest, null, account, "id", OperationTargetType.TYPE_ACCOUNT);

        return accountOutput;
    }

    @Override
    @ApiOperation(value = "检查超级管理员是否存在")
    public Map<String, Object> checkRoot() {

        Integer count = accountDao.getRootsCount();
        Map<String, Object> params = new HashMap<>();

        if (count == 0) {
            params.put("rootExist", false);
        } else {
            params.put("rootExist", true);
        }

        return params;
    }

    @Override
    @ApiOperation(value = "创建超级管理员")
    public AccountOutput createRoot(@Validated @RequestBody CreateRootAccountInput createRootAccountInput) {

        Integer count = accountDao.getRootsCount();

        if (count != 0) {
            throw new StatusException(AccountStatusCode.ACCOUNT_ROOT_IS_EXIST);
        }

        Account account = new Account();
        BeanUtils.copyProperties(createRootAccountInput, account);

        String salt = CryptoUtil.salt();
        String password = LdapUtil.ssha(createRootAccountInput.getPassword(), salt);

        account.setStatus(1);
        account.setSalt(salt);
        account.setPassword(password);
        account.setName(createRootAccountInput.getName());
        account.setType(1);
        accountDao.createAccount(account);

        if (ldapConfig.enabled) {
            AccountEntry accountEntry = new AccountEntry();
            accountEntry.setUid(account.getUsername());
            accountEntry.setName(account.getName());
            accountEntry.setUsername(account.getUsername());
            accountEntry.setPassword("{ssha}" + account.getPassword());
            accountEntry.setEmail(account.getEmail());
            accountEntry.setMobile(account.getMobile());
            accountEntryDao.save(accountEntry);
        }

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account, accountOutput);

        accountOutput.setStatusText(langUtil.getEnumsMessage("accounts.status", String.valueOf(accountOutput.getStatus())));

        return accountOutput;
    }

    @Override
    @ApiOperation(value = "获取账户列表")
    public GetAccountsOutput getAccounts(String page, String size) {

        Integer queryPage = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        Integer maxSize = Integer.parseInt(maxAccountsSize);
        Integer pageSize = (StringUtils.isEmpty(size) || Integer.parseInt(size) > maxSize) ? maxSize : Integer.parseInt(size);
        List<AccountOutput> accountOutputs = accountDao.getAccounts((queryPage - 1) * pageSize, pageSize);
        Integer count = accountDao.getAccountsCount();

        for (AccountOutput accountOutput : accountOutputs) {
            accountOutput.setStatusText(langUtil.getEnumsMessage("accounts.status", String.valueOf(accountOutput.getStatus())));
        }

        GetAccountsOutput getAccountsOutput = new GetAccountsOutput(accountOutputs, count);

        return getAccountsOutput;
    }

    @Override
    @ApiOperation(value = "获取账户信息")
    public AccountOutput getAccount(String id) {

        if (StringUtils.isEmpty(id)) {
            throw new StatusException(AccountStatusCode.PARAMETER_ERROR);
        }

        AccountOutput account = accountDao.getAccountOutputById(Integer.parseInt(id));

        return account;
    }

    @Override
    @ApiOperation(value = "获取总账户数")
    public Integer getAccountsCount() {

        return accountDao.getAccountsCount();
    }

    @Override
    @ApiOperation(value = "更新用户")
    @Transactional
    public AccountOutput updateAccount(@Validated @RequestBody UpdateAccountInput updateAccountInput) throws InvalidNameException {

        Guest guest = ThreadLocalUtil.getGuest();
        Account account = new Account();
        BeanUtils.copyProperties(updateAccountInput, account);
        Account oldAccount = accountDao.getAccountById(updateAccountInput.getId());
        Account newAccount = new Account();
        account.setOperatorId(guest.getId());
        account.setOperatorName(guest.getName());
        account.setOperatorIp(guest.getIp());
        Integer count = accountDao.updateAccount(account);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.PUT_ACCOUNT_FAILED);
        }

        if (ldapConfig.enabled) {
            LdapName accountEntryId = new LdapName(String.format("uid=%s,o=accounts", oldAccount.getUsername()));
            AccountEntry accountEntry = accountEntryDao.findById(accountEntryId).get();
            accountEntry.setUid(account.getUsername());
            accountEntry.setName(account.getName());
            accountEntry.setUsername(account.getUsername());
            accountEntry.setEmail(account.getEmail());
            accountEntry.setMobile(account.getMobile());
            accountEntry.setPassword(oldAccount.getPassword());
            accountEntryDao.save(accountEntry);
        }

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account, accountOutput);
        accountOutput.setStatus(oldAccount.getStatus());

        accountOutput.setStatusText(langUtil.getEnumsMessage("accounts.status", String.valueOf(accountOutput.getStatus())));
        AccountGroup group = accountGroupDao.getAccountGroupById(accountOutput.getGroupId());
        accountOutput.setGroupName(group.getName());

        BeanUtils.copyProperties(accountOutput, newAccount);
        operationLogService.saveOperationLog(guest, oldAccount, newAccount, "id", OperationTargetType.TYPE_ACCOUNT);

        return accountOutput;
    }

    @Override
    @ApiOperation(value = "重置账户密码")
    public void resetAccountPassword(Integer id) throws InvalidNameException {

        Guest guest = ThreadLocalUtil.getGuest();
        String salt = CryptoUtil.salt();
        String password = LdapUtil.ssha(accountDefaultPassword, salt);
        Account account = accountDao.getAccountById(id);

        if (account == null) {
            throw new StatusException(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }
        account.setSalt(salt);
        account.setPassword(password);
        account.setOperatorId(guest.getId());
        account.setOperatorName(guest.getName());
        account.setOperatorIp(guest.getIp());
        Integer count = accountDao.updateAccountPassword(account);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.PUT_ACCOUNT_PASSWORD_FAILED);
        }

        if (ldapConfig.enabled) {
            LdapName accountEntryId = new LdapName(String.format("uid=%s,o=accounts", account.getUsername()));
            AccountEntry accountEntry = accountEntryDao.findById(accountEntryId).get();
            accountEntry.setPassword(account.getPassword());
            accountEntryDao.save(accountEntry);
        }


        operationLogService.saveOperationLog(guest, account, account, "id", OperationTargetType.TYPE_ACCOUNT);
    }

    @Override
    @ApiOperation(value = "修改账户密码")
    public void updateAccountPassword(@Validated @RequestBody UpdateAccountPasswordInput updateAccountPasswordInput) throws InvalidNameException {

        Guest guest = ThreadLocalUtil.getGuest();
        String salt = CryptoUtil.salt();
        String password = LdapUtil.ssha(updateAccountPasswordInput.getNewPassword(), salt);
        Account oldValue = accountDao.getAccountById(updateAccountPasswordInput.getId());

        Account account = new Account();
        account.setId(updateAccountPasswordInput.getId());
        account.setPassword(password);
        account.setSalt(salt);
        account.setOperatorId(guest.getId());
        account.setOperatorName(guest.getName());
        account.setOperatorIp(guest.getIp());
        Integer count = accountDao.updateAccountPassword(account);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.PUT_ACCOUNT_PASSWORD_FAILED);
        }

        if (ldapConfig.enabled) {
            LdapName accountEntryId = new LdapName(String.format("uid=%s,o=accounts", account.getUsername()));
            AccountEntry accountEntry = accountEntryDao.findById(accountEntryId).get();
            accountEntry.setPassword(account.getPassword());
            accountEntryDao.save(accountEntry);
        }

        operationLogService.saveOperationLog(guest, oldValue, account, "id", OperationTargetType.TYPE_ACCOUNT);
    }

    @Override
    @ApiOperation(value = "更新用户状态")
    public AccountOutput updateAccountStatus(@RequestBody UpdateAccountStatusInput updateAccountStatusInput) throws InvalidNameException {

        Guest guest = ThreadLocalUtil.getGuest();
        Account oldValue = accountDao.getAccountById(updateAccountStatusInput.getId());
        Account newValue = new Account();
        Account account = new Account();
        BeanUtils.copyProperties(updateAccountStatusInput, account);
        account.setOperatorId(guest.getId());
        account.setOperatorName(guest.getName());
        account.setOperatorIp(guest.getIp());
        Integer count = accountDao.updateAccountStatus(account);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.PUT_ACCOUNT_STATUS_FAILED);
        }

        if (ldapConfig.enabled && account.getStatus().equals(1)) {
            AccountEntry accountEntry = new AccountEntry();
            account = accountDao.getAccountById(account.getId());
            accountEntry.setUid(account.getUsername());
            accountEntry.setName(account.getName());
            accountEntry.setUsername(account.getUsername());
            accountEntry.setPassword("{ssha}" + account.getPassword());
            accountEntry.setEmail(account.getEmail());
            accountEntry.setMobile(account.getMobile());
            accountEntryDao.save(accountEntry);
        }

        if (ldapConfig.enabled && account.getStatus().equals(2)) {
            account = accountDao.getAccountById(account.getId());
            LdapName accountEntryId = new LdapName(String.format("uid=%s,o=accounts", account.getUsername()));
            AccountEntry accountEntry = accountEntryDao.findById(accountEntryId).get();
            accountEntryDao.delete(accountEntry);
        }

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account, accountOutput);

        accountOutput.setStatusText(langUtil.getEnumsMessage("accounts.status", String.valueOf(accountOutput.getStatus())));
        BeanUtils.copyProperties(accountOutput, newValue);

        operationLogService.saveOperationLog(guest, oldValue, newValue, "id", OperationTargetType.TYPE_ACCOUNT);

        return accountOutput;
    }

    @Override
    @ApiOperation(value = "获取用户拥有的所有权限")
    public List<String> getAccountPermissions(@RequestParam("id") Integer id) {

        Account account = accountDao.getAccountById(id);
        List<String> permissions = new ArrayList<>();

        if (account != null && account.getType() == 1) {
            permissions.add("*");
            return permissions;
        }

        permissions = accountDao.getAccountPermissionsByAccountId(id);

        return permissions;
    }

    @Override
    @ApiOperation(value = "获取用户某权限对应的数据权限")
    public List<String> getAccountPermissionDataScope(@RequestParam("id") Integer id, @RequestParam("code") String code) {

        List<String> dataScope = accountDao.getAccountPermissionDataScope(id, code);

        return dataScope;
    }


    @Override
    @ApiOperation(value = "获取有效账户数量")
    public Integer getValidAccountsCount() {

        return accountDao.getValidAccountsCount();
    }

    @Override
    @ApiOperation(value = "账户校验")
    public void validateAccount(@Validated @RequestBody ValidateAccountInput validateAccountInput) {

        Account account = accountDao.getAccountById(validateAccountInput.getId());

        if (account == null) {
            throw new StatusException(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }

        String salt = account.getSalt();
        String passwordLegal = account.getPassword();

        if (!passwordLegal.equals(LdapUtil.ssha(validateAccountInput.getPassword(), salt))) {
            throw new StatusException(AccountStatusCode.ACCOUNT_PASSWORD_ERROR);
        }

    }

    @Override
    @ApiOperation(value = "查询账户名称")
    public AccountOutput getAccountById(Integer id) {
        Account account = accountDao.getAccountById(id);
        return BeanCopyUtil.copy(account, AccountOutput.class);
    }
}
