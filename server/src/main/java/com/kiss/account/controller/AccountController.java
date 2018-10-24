package com.kiss.account.controller;

import com.kiss.account.client.AccountClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.entity.AccountRole;
import com.kiss.account.input.*;
import com.kiss.account.output.*;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.DbEnumUtil;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.utils.GuestUtil;
import com.kiss.account.validator.AccountValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Account", description = "账户相关接口")
public class AccountController implements AccountClient {

    @Autowired
    private AccountGroupDao accountGroupDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountValidator accountValidator;

    @Value("${max.accounts.size}")
    private String maxAccountsSize;

    @Value("${account.default.password}")
    private String accountDefaultPassword;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @Override
    @ApiOperation(value = "添加账户")
    public ResultOutput<AccountOutput> createAccount(@Validated @RequestBody CreateAccountInput createAccountInput) {

        Account account = new Account();
        BeanUtils.copyProperties(createAccountInput, account);
        String salt = CryptoUtil.salt();
        String password = CryptoUtil.hmacSHA256(createAccountInput.getPassword(), salt);
        account.setStatus(1);
        account.setSalt(salt);
        account.setPassword(password);
        account.setName(createAccountInput.getName());
        account.setOperatorId(GuestUtil.getGuestId());
        account.setOperatorIp("127.0.0.4");
        account.setOperatorName(GuestUtil.getName());
        accountDao.createAccount(account);

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account, accountOutput);
        AccountGroup group = accountGroupDao.getGroupById(account.getGroupId());
        accountOutput.setGroupName(group.getName());
        accountOutput.setStatusText(DbEnumUtil.getValue("accounts.status", String.valueOf(accountOutput.getStatus())));

        return ResultOutputUtil.success(accountOutput);
    }

    @Override
    @ApiOperation(value = "绑定账户角色")
    @Transactional
    public ResultOutput<List<AccountRoleOutput>> bindAccountRoles(@Validated @RequestBody BindRoleToAccountInput bindRoleToAccountInput) {

        List<Integer> roles = bindRoleToAccountInput.getRoleId();
        List<AccountRole> accountRolesList = new ArrayList<>();

        for (Integer roleId : roles) {
            AccountRole accountRoles = new AccountRole();
            accountRoles.setOperatorId(123);
            accountRoles.setOperatorIp("127.0.0.4");
            accountRoles.setOperatorName("koy");
            accountRoles.setAccountId(bindRoleToAccountInput.getAccountId());
            accountRoles.setRoleId(roleId);
            accountRolesList.add(accountRoles);
        }

        accountDao.deleteAccountRoles(bindRoleToAccountInput.getAccountId());
        accountDao.bindRolesToAccount(accountRolesList);

        List<AccountRoleOutput> accountRoleOutputs = new ArrayList<>();

        for (AccountRole accountRole : accountRolesList) {
            AccountRoleOutput accountRoleOutput = new AccountRoleOutput();
            BeanUtils.copyProperties(accountRole,accountRoleOutput);
            accountRoleOutputs.add(accountRoleOutput);
        }

        return ResultOutputUtil.success(accountRoleOutputs);
    }

    @Override
    @ApiOperation(value = "获取账户列表")
    public ResultOutput<GetAccountsOutput> getAccounts(String page, String size) {

        Integer queryPage = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        Integer maxSize = Integer.parseInt(maxAccountsSize);
        Integer pageSize = (StringUtils.isEmpty(size) || Integer.parseInt(size) > maxSize) ? maxSize : Integer.parseInt(size);
        List<Account> accounts = accountDao.getAccounts((queryPage - 1) * pageSize, pageSize);
        Integer count = accountDao.getAccountsCount();
        List<AccountOutput> accountOutputs = new ArrayList<>();

        for (Account account : accounts) {
            AccountOutput accountOutput = new AccountOutput();
            BeanUtils.copyProperties(account,accountOutput);
            accountOutput.setStatusText(DbEnumUtil.getValue("accounts.status", String.valueOf(accountOutput.getStatus())));
            accountOutputs.add(accountOutput);
        }

        GetAccountsOutput getAccountsOutput = new GetAccountsOutput(accountOutputs, count);

        return ResultOutputUtil.success(getAccountsOutput);
    }

    @Override
    @ApiOperation(value = "获取账户信息")
    public ResultOutput<AccountOutput> getAccount(String id) {
        if (StringUtils.isEmpty(id)) {
            return ResultOutputUtil.error(AccountStatusCode.PARAMETER_ERROR);
        }
        AccountOutput account = accountDao.getAccountOutputById(Integer.parseInt(id));
        return ResultOutputUtil.success(account);
    }

    @Override
    @ApiOperation(value = "获取总账户数")
    public ResultOutput<Integer> getAccountsCount() {

        Integer count = accountDao.getAccountsCount();

        return ResultOutputUtil.success(count);
    }

    @Override
    @ApiOperation(value = "更新用户")
    public ResultOutput<AccountOutput> updateAccount(@Validated @RequestBody UpdateAccountInput updateAccountInput) {

        Account account = new Account();
        BeanUtils.copyProperties(updateAccountInput, account);
        Integer count = accountDao.updateAccount(account);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_ACCOUNT_FAILED);
        }

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account,accountOutput);
        accountOutput.setStatusText(DbEnumUtil.getValue("accounts.status", String.valueOf(accountOutput.getStatus())));
        AccountGroup group = accountGroupDao.getGroupById(accountOutput.getGroupId());
        accountOutput.setGroupName(group.getName());

        return ResultOutputUtil.success(accountOutput);
    }

    @Override
    @ApiOperation(value = "重置账户密码")
    public ResultOutput updateAccountPassword(Integer id) {

        String salt = CryptoUtil.salt();
        String password = CryptoUtil.hmacSHA256(accountDefaultPassword, salt);

        Account account = accountDao.getAccountById(id);

        if (account == null) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }

        account.setSalt(salt);
        account.setPassword(password);
        Integer count = accountDao.updateAccountPassword(account);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_ACCOUNT_PASSWORD_FAILED);
        }

        return ResultOutputUtil.success();
    }

    @Override
    @ApiOperation(value = "更新用户状态")
    public ResultOutput updateAccountStatus(@RequestBody UpdateAccountStatusInput updateAccountStatusInput) {

        Account account = new Account();
        BeanUtils.copyProperties(updateAccountStatusInput, account);
        Integer count = accountDao.updateAccountStatus(account);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_ACCOUNT_STATUS_FAILED);
        }

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account,accountOutput);
        accountOutput.setStatusText(DbEnumUtil.getValue("accounts.status", String.valueOf(accountOutput.getStatus())));


        return ResultOutputUtil.success(accountOutput);
    }

    @Override
    @ApiOperation(value = "获取账户所有权限")
    public ResultOutput getAccountPermissions(@RequestParam("id") Integer id) {

        Account account = accountDao.getAccountById(id);
        List<String> permissions = new ArrayList<>();

        if (account != null && account.getType() == 1) {
            permissions.add("*");
            return ResultOutputUtil.success(permissions);
        }

        permissions = accountDao.getAccountPermissions(id);

        return ResultOutputUtil.success(permissions);
    }

    @Override
    @ApiOperation(value = "获取账户所有数据权限")
    public ResultOutput getAccountPermissionDataScope(@RequestParam("id") Integer id, @RequestParam("code") String code) {

        List<String> dataScope = accountDao.getAccountPermissionDataScope(id, code);
        return ResultOutputUtil.success(dataScope);
    }

    @Override
    @ApiOperation(value = "获取有效账户数量")
    public ResultOutput getValidAccountsCount() {

        Integer count = accountDao.getValidAccountsCount();
        return ResultOutputUtil.success(count);
    }
}
