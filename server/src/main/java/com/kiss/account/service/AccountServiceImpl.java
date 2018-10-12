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
import com.kiss.account.out.Code;
import com.kiss.account.out.Message;
import com.kiss.account.output.*;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Account", description = "账户相关接口")
public class AccountServiceImpl implements AccountClient {

    @Autowired
    private AccountGroupDao accountGroupDao;

    @Autowired
    private AccountDao accountDao;

    @Value("${max.accounts.size}")
    private String maxAccountsSize;

    @Override
    @ApiOperation(value = "创建部门")
    public ResultOutput<AccountGroupOutput> postAccountGroups(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput) {
        AccountGroup accountGroup = new AccountGroup();
        BeanUtils.copyProperties(createAccountGroupInput, accountGroup);
        accountGroup.setLevel("0");
        accountGroup.setSeq(0);
        accountGroup.setOperatorId(123);
        accountGroup.setOperatorIp("127.0.0.1");
        accountGroup.setOperatorName("张三");
        accountGroupDao.createAccountGroup(accountGroup);
        AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
        BeanUtils.copyProperties(accountGroup,accountGroupOutput);
        return ResultOutputUtil.success(accountGroupOutput);
    }

    @Override
    @ApiOperation(value = "添加账户")
    public ResultOutput<AccountOutput> postAccounts(@Validated @RequestBody CreateAccountInput createAccountInput) {
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
        AccountOutput accountOutput = new AccountOutput();
        BeanUtils.copyProperties(account,accountOutput);
        AccountGroup group = accountDao.getGroup(account.getGroupId());
        accountOutput.setGroupName(group.getName());
        return ResultOutputUtil.success(accountOutput);
    }

    @Override
    @ApiOperation(value = "绑定账户角色")
    public ResultOutput<List<AccountRolesOutput>> postAccountsRole(@Validated @RequestBody AllocateRoleToAccountInput allocateRoleToAccountInput) {
        List<Integer> roles = allocateRoleToAccountInput.getRoleId();
        List<AccountRolesOutput> accountRolesList = new ArrayList<>();
        for (Integer roleId : roles) {
            AccountRolesOutput accountRoles = new AccountRolesOutput();
            accountRoles.setOperatorId(123);
            accountRoles.setOperatorIp("127.0.0.4");
            accountRoles.setOperatorName("李四");
            accountRoles.setAccountId(allocateRoleToAccountInput.getAccountId());
            accountRoles.setRoleId(roleId);
            accountRolesList.add(accountRoles);
        }

        accountDao.allocateRolesToAccount(accountRolesList);
        return ResultOutputUtil.success(accountRolesList);
    }

    @Override
    @ApiOperation(value = "获取账户列表")
    public ResultOutput<GetAccountsOutput> getAccounts(String page, String size) {
        Integer queryPage = StringUtils.isEmpty(page)? 1 : Integer.parseInt(page);
        Integer maxSize = Integer.parseInt(maxAccountsSize);
        Integer pageSize = (StringUtils.isEmpty(size) || Integer.parseInt(size) > maxSize )? maxSize: Integer.parseInt(size);
        List<AccountOutput> accounts = accountDao.getAccounts((queryPage - 1)*pageSize,pageSize);
        Integer count = accountDao.getAccountsCount();
        GetAccountsOutput getAccountsOutput = new GetAccountsOutput(accounts,count);
        return ResultOutputUtil.success(getAccountsOutput);
    }

    @Override
    @ApiOperation(value = "添加账户信息")
    public ResultOutput<AccountOutput> getAccount(String id) {
        AccountOutput account = accountDao.getAccountById(Integer.parseInt(id));
        return ResultOutputUtil.success(account);
    }

    @Override
    public ResultOutput<AccountGroupOutput> getGroups() {
        List<AccountGroup> groups = accountDao.getGroups();
        List<AccountGroupOutput> groupsOutput = new ArrayList<>();
        for (AccountGroup accountGroup : groups) {
            AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
            BeanUtils.copyProperties(accountGroup,accountGroupOutput);
            groupsOutput.add(accountGroupOutput);
        }
        return ResultOutputUtil.success(groupsOutput);
    }

    @Override
    public ResultOutput<AccountGroupOutput> getGroup(String id) {
        AccountGroup group = accountDao.getGroup(Integer.parseInt(id));
        AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
        BeanUtils.copyProperties(group,accountGroupOutput);
        return ResultOutputUtil.success(accountGroupOutput);
    }

    @Override
    public ResultOutput<Integer> getAccountsCount() {
        Integer count = accountDao.getAccountsCount();
        return ResultOutputUtil.success(count);
    }

    @Override
    public ResultOutput get() {
        System.out.println("hello");
//        System.out.println(getMessage.messageSource);

        System.out.println(Message.getMessage("zh-CN",900));
        System.out.println(Message.getMessage("en",170));
        return ResultOutputUtil.error(Code.PARAMETER_ERROR,"ascsd");
    }
}
