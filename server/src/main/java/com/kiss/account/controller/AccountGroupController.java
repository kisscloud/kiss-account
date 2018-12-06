package com.kiss.account.controller;

import com.kiss.account.client.AccountGroupClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import com.kiss.account.service.OperationLogService;
import com.kiss.account.entity.OperationTargetType;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.validator.AccountGroupValidator;
import entity.Guest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;
import utils.ThreadLocalUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Group", description = "部门相关接口")
public class AccountGroupController implements AccountGroupClient {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountGroupDao accountGroupDao;

    @Autowired
    private AccountGroupValidator accountGroupValidator;

    @Autowired
    private OperationLogService operationLogService;

    @Value("${max.accounts.size}")
    private String maxAccountsSize;

    @Value("${account.default.password}")
    private String accountDefaultPassword;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(accountGroupValidator);
    }

    @Override
    @ApiOperation(value = "创建部门")
    public ResultOutput<AccountGroupOutput> createAccountGroup(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput) {

        Guest guest = ThreadLocalUtil.getGuest();
        AccountGroup accountGroup = accountGroupDao.getAccountGroupByName(createAccountGroupInput.getName());

        if (accountGroup != null) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_GROUP_NAME_EXIST);
        }

        accountGroup = new AccountGroup();
        BeanUtils.copyProperties(createAccountGroupInput, accountGroup);
        accountGroup.setLevel("0,");
        accountGroup.setSeq(0);
        accountGroup.setOperatorId(guest.getId());
        accountGroup.setOperatorIp(guest.getIp());
        accountGroup.setOperatorName(guest.getName());
        accountGroupDao.createAccountGroup(accountGroup);
        AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
        BeanUtils.copyProperties(accountGroup, accountGroupOutput);
        operationLogService.saveOperationLog(guest,null,accountGroup,"id",OperationTargetType.TYPE_ACCOUNT_GROUP);

        return ResultOutputUtil.success(accountGroupOutput);
    }

    @Override
    @ApiOperation(value = "获取部门列表")
    public ResultOutput<AccountGroupOutput> getGroups() {

        List<AccountGroup> groups = accountGroupDao.getAccountGroups();
        List<AccountGroupOutput> groupsOutput = new ArrayList<>();

        for (AccountGroup accountGroup : groups) {
            AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
            BeanUtils.copyProperties(accountGroup, accountGroupOutput);
            groupsOutput.add(accountGroupOutput);
        }

        return ResultOutputUtil.success(groupsOutput);
    }

    @Override
    @ApiOperation(value = "获取部门信息")
    public ResultOutput<AccountGroupOutput> getGroup(Integer id) {

        AccountGroup group = accountGroupDao.getAccountGroupById(id);
        AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
        BeanUtils.copyProperties(group, accountGroupOutput);

        return ResultOutputUtil.success(accountGroupOutput);
    }

    @Override
    @ApiOperation(value = "删除部门")
    public ResultOutput deleteGroup(@RequestParam("id") Integer id) {

        Guest guest = ThreadLocalUtil.getGuest();
        List<Account> accounts = accountDao.getAccountsByGroupId(id);

        if (accounts != null && !accounts.isEmpty()) {
            return ResultOutputUtil.error(AccountStatusCode.NOT_EMPTY_GROUP);
        }

        List<AccountGroup> accountGroups = accountGroupDao.getAccountGroupChildrenByParentId(id);

        if (accountGroups != null && !accountGroups.isEmpty()) {
            return ResultOutputUtil.error(AccountStatusCode.NOT_EMPTY_GROUP);
        }

        AccountGroup oldValue = accountGroupDao.getAccountGroupById(id);
        accountGroupDao.deleteAccountGroupById(id);
        operationLogService.saveOperationLog(guest,oldValue,null,"id",OperationTargetType.TYPE_ACCOUNT_GROUP);

        return ResultOutputUtil.success();
    }

    @Override
    @ApiOperation(value = "更新部门")
    public ResultOutput updateAccountGroup(@Valid @RequestBody UpdateAccountGroupInput updateAccountGroupInput) {

        Guest guest = ThreadLocalUtil.getGuest();
        AccountGroup oldValue = accountGroupDao.getAccountGroupById(updateAccountGroupInput.getId());
        AccountGroup accountGroup = new AccountGroup();
        BeanUtils.copyProperties(updateAccountGroupInput, accountGroup);
        accountGroup.setOperatorId(guest.getId());
        accountGroup.setOperatorIp(guest.getIp());
        accountGroup.setOperatorName(guest.getName());
        Integer count = accountGroupDao.updateAccountGroup(accountGroup);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_ACCOUNT_GROUP_FAILED);
        }

        AccountGroup newValue = accountGroupDao.getAccountGroupById(updateAccountGroupInput.getId());
        operationLogService.saveOperationLog(guest,oldValue,newValue,"id",OperationTargetType.TYPE_ACCOUNT_GROUP);

        return ResultOutputUtil.success(accountGroup);
    }

    @Override
    @ApiOperation(value = "获取部门数")
    public ResultOutput getAccountGroupsCount() {

        Integer count = accountGroupDao.getAccountGroupCount();

        return ResultOutputUtil.success(count);
    }
}
