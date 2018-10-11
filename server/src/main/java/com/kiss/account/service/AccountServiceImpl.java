package com.kiss.account.service;

import com.kiss.account.client.AccountClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.entity.AccountRoles;
import com.kiss.account.input.AllocateRoleToAccountInput;
import com.kiss.account.input.CreateAccountGroupInput;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.output.ResultOutput;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Account", description = "账户相关接口")
public class AccountServiceImpl implements AccountClient {

    @Autowired
    private AccountGroupDao accountGroupDao;

    @Autowired
    private AccountDao accountDao;

    @Override
    @ApiOperation(value = "创建部门")
    public ResultOutput postAccountGroups(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput) {
        AccountGroup accountGroup = new AccountGroup();
        BeanUtils.copyProperties(createAccountGroupInput, accountGroup);
        accountGroup.setLevel("0");
        accountGroup.setSeq(0);
        accountGroup.setOperatorId(123);
        accountGroup.setOperatorIp("127.0.0.1");
        accountGroup.setOperatorName("张三");
        accountGroupDao.createAccountGroup(accountGroup);

        return ResultOutputUtil.success(accountGroup);
    }

    @Override
    @ApiOperation(value = "添加账户")
    public ResultOutput postAccounts(@Validated @RequestBody CreateAccountInput createAccountInput) {
        Account account = new Account();
        BeanUtils.copyProperties(createAccountInput, account);
        String salt = CryptoUtil.salt();
        String password = CryptoUtil.hmacSHA256(createAccountInput.getPassword(), salt);
        account.setSalt(salt);
        account.setPassword(password);
        account.setName(createAccountInput.getName());
        account.setOperatorId(123);
        account.setOperatorIp("127.0.0.4");
        account.setOperatorName("李四");
        accountDao.createAccount(account);

        return ResultOutputUtil.success(account);
    }

    @Override
    @ApiOperation(value = "绑定账户角色")
    public ResultOutput postAccountsRole(@Validated @RequestBody AllocateRoleToAccountInput allocateRoleToAccountInput) {
        List<Integer> roles = allocateRoleToAccountInput.getRoleId();
        List<AccountRoles> accountRolesList = new ArrayList<>();
        for (Integer roleId : roles) {
            AccountRoles accountRoles = new AccountRoles();
            accountRoles.setOperatorId(123);
            accountRoles.setOperatorIp("127.0.0.4");
            accountRoles.setOperatorName("李四");
            accountRoles.setAccountId(allocateRoleToAccountInput.getAccountId());
            accountRoles.setRoleId(roleId);
            accountRolesList.add(accountRoles);
        }

        accountDao.allocateRolesToAccount(accountRolesList);
        return ResultOutputUtil.success();
    }

    @Override
    @ApiOperation(value = "获取账户列表")
    public ResultOutput getAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Account> accounts = accountDao.getAccounts();
        return ResultOutputUtil.success(accounts);
    }

    @Override
    @ApiOperation(value = "添加账户信息")
    public ResultOutput getAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Account account = accountDao.getAccountById(Integer.parseInt(id));
        return ResultOutputUtil.success(account);
    }

    @Override
    public String get() {
        System.out.println("hello");
        return "hello ni hao";
    }
}
