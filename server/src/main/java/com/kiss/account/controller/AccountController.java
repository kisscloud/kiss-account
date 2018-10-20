package com.kiss.account.controller;

import com.kiss.account.client.AccountClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.input.*;
import com.kiss.account.output.*;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.DbEnumUtil;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.utils.UserUtil;
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
        account.setSalt(salt);
        account.setPassword(password);
        account.setName(createAccountInput.getName());
        account.setOperatorId(UserUtil.getUserId());
        account.setOperatorIp("127.0.0.4");
        account.setOperatorName(UserUtil.getUsername());
        accountDao.createAccount(account);
        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account, accountOutput);
        AccountGroup group = accountGroupDao.getGroupById(account.getGroupId());
        accountOutput.setGroupName(group.getName());

        return ResultOutputUtil.success(accountOutput);
    }

    @Override
    @ApiOperation(value = "绑定账户角色")
    @Transactional
    public ResultOutput<List<AccountRoleOutput>> bindAccountRoles(@Validated @RequestBody BindRoleToAccountInput bindRoleToAccountInput) {

        List<Integer> roles = bindRoleToAccountInput.getRoleId();
        List<AccountRoleOutput> accountRolesList = new ArrayList<>();

        for (Integer roleId : roles) {
            AccountRoleOutput accountRoles = new AccountRoleOutput();
            accountRoles.setOperatorId(123);
            accountRoles.setOperatorIp("127.0.0.4");
            accountRoles.setOperatorName("koy");
            accountRoles.setAccountId(bindRoleToAccountInput.getAccountId());
            accountRoles.setRoleId(roleId);
            accountRolesList.add(accountRoles);
        }

        accountDao.deleteAccountRoles(bindRoleToAccountInput.getAccountId());
        accountDao.bindRolesToAccount(accountRolesList);

        return ResultOutputUtil.success(accountRolesList);
    }

    @Override
    @ApiOperation(value = "获取账户列表")
    public ResultOutput<GetAccountsOutput> getAccounts(String page, String size) {

        Integer queryPage = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        Integer maxSize = Integer.parseInt(maxAccountsSize);
        Integer pageSize = (StringUtils.isEmpty(size) || Integer.parseInt(size) > maxSize) ? maxSize : Integer.parseInt(size);
        List<AccountOutput> accounts = accountDao.getAccounts((queryPage - 1) * pageSize, pageSize);
        Integer count = accountDao.getAccountsCount();
        for (AccountOutput accountOutput : accounts) {
            accountOutput.setStatusText(DbEnumUtil.getValue("accounts", String.valueOf(accountOutput.getStatus())));
        }
        GetAccountsOutput getAccountsOutput = new GetAccountsOutput(accounts, count);

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

    public ResultOutput get(@Valid @RequestBody AccountInfoInput accountInfoInput) {
        return ResultOutputUtil.success("++++" + UserUtil.getUsername() + "=====" + UserUtil.getUserId());
    }

    @Override
    @ApiOperation(value = "更新用户")
    public ResultOutput<AccountOutput> updateAccount(@Validated @RequestBody UpdateAccountInput updateAccountInput) {

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(updateAccountInput, accountOutput);
        Integer count = accountDao.updateAccount(accountOutput);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_ACCOUNT_FAILED);
        }

        return ResultOutputUtil.success(accountOutput);
    }

    @Override
    @ApiOperation(value = "重置账户密码")
    public ResultOutput updateAccountPassword(Integer id) {

        String salt = CryptoUtil.salt();
        String password = CryptoUtil.hmacSHA256(accountDefaultPassword, salt);
        Account account = new Account();
        account.setId(id);
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

        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(updateAccountStatusInput, accountOutput);
        Integer count = accountDao.updateAccountStatus(accountOutput);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_ACCOUNT_STATUS_FAILED);
        }

        return ResultOutputUtil.success(accountOutput);
    }

    @Override
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
    public ResultOutput getAccountPermissionDataScope(@RequestParam("id") Integer id, @RequestParam("code") String code) {

        List<String> dataScope = accountDao.getAccountPermissionDataScope(id, code);
        return ResultOutputUtil.success(dataScope);
    }

    public ResultOutput verifyAccountExistType(Account account, String name, String username, String email, String mobile) {
        if (!StringUtils.isEmpty(account.getName()) && account.getName().equals(name)) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_NAME_EXIST);
        }

        if (!StringUtils.isEmpty(account.getUsername()) && account.getUsername().equals(username)) {
            return ResultOutputUtil.error(AccountStatusCode.USERNAME_EXIST);
        }

        if (!StringUtils.isEmpty(account.getEmail()) && account.getEmail().equals(email)) {
            return ResultOutputUtil.error(AccountStatusCode.EMAIL_EXIST);
        }

        if (!StringUtils.isEmpty(account.getMobile()) && account.getMobile().equals(mobile)) {
            return ResultOutputUtil.error(AccountStatusCode.MOBILE_EXIST);
        }

        return ResultOutputUtil.error(AccountStatusCode.Account_EXIST);
    }
}
