package com.kiss.account.controller;

import com.kiss.account.client.AccountGroupClient;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import com.kiss.account.entity.OperationTargetType;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.validator.AccountGroupValidator;
import entity.Guest;
import exception.StatusException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import utils.ThreadLocalUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Group", description = "部门相关接口")
public class AccountGroupController extends BaseController implements AccountGroupClient {


    @Autowired
    private AccountGroupValidator accountGroupValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(accountGroupValidator);
    }

    @Override
    @ApiOperation(value = "创建部门")
    public AccountGroupOutput createAccountGroup(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput) {

        Guest guest = ThreadLocalUtil.getGuest();
        AccountGroup accountGroup = accountGroupDao.getAccountGroupByName(createAccountGroupInput.getName());

        if (accountGroup != null) {
            throw new StatusException(AccountStatusCode.ACCOUNT_GROUP_NAME_EXIST);
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
        operationLogService.saveOperationLog(guest, null, accountGroup, "id", OperationTargetType.TYPE_ACCOUNT_GROUP);

        return accountGroupOutput;
    }

    @Override
    @ApiOperation(value = "获取部门列表")
    public List<AccountGroupOutput> getGroups() {

        List<AccountGroup> groups = accountGroupDao.getAccountGroups();
        List<AccountGroupOutput> groupsOutput = new ArrayList<>();

        for (AccountGroup accountGroup : groups) {
            AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
            BeanUtils.copyProperties(accountGroup, accountGroupOutput);
            groupsOutput.add(accountGroupOutput);
        }

        return groupsOutput;
    }

    @Override
    @ApiOperation(value = "获取部门信息")
    public AccountGroupOutput getGroup(Integer id) {

        AccountGroup group = accountGroupDao.getAccountGroupById(id);
        AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
        BeanUtils.copyProperties(group, accountGroupOutput);

        return accountGroupOutput;
    }

    @Override
    @ApiOperation(value = "删除部门")
    public void deleteGroup(@RequestParam("id") Integer id) {

        Guest guest = ThreadLocalUtil.getGuest();
        List<Account> accounts = accountDao.getAccountsByGroupId(id);

        if (accounts != null && !accounts.isEmpty()) {
            throw new StatusException(AccountStatusCode.NOT_EMPTY_GROUP);
        }

        List<AccountGroup> accountGroups = accountGroupDao.getAccountGroupChildrenByParentId(id);

        if (accountGroups != null && !accountGroups.isEmpty()) {
            throw new StatusException(AccountStatusCode.NOT_EMPTY_GROUP);
        }

        AccountGroup oldValue = accountGroupDao.getAccountGroupById(id);
        accountGroupDao.deleteAccountGroupById(id);
        operationLogService.saveOperationLog(guest, oldValue, null, "id", OperationTargetType.TYPE_ACCOUNT_GROUP);
    }

    @Override
    @ApiOperation(value = "更新部门")
    public AccountGroupOutput updateAccountGroup(@Valid @RequestBody UpdateAccountGroupInput updateAccountGroupInput) {

        Guest guest = ThreadLocalUtil.getGuest();
        AccountGroup oldValue = accountGroupDao.getAccountGroupById(updateAccountGroupInput.getId());
        AccountGroup accountGroup = new AccountGroup();
        BeanUtils.copyProperties(updateAccountGroupInput, accountGroup);
        accountGroup.setOperatorId(guest.getId());
        accountGroup.setOperatorIp(guest.getIp());
        accountGroup.setOperatorName(guest.getName());
        Integer count = accountGroupDao.updateAccountGroup(accountGroup);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.PUT_ACCOUNT_GROUP_FAILED);
        }

        AccountGroup newValue = accountGroupDao.getAccountGroupById(updateAccountGroupInput.getId());
        operationLogService.saveOperationLog(guest, oldValue, newValue, "id", OperationTargetType.TYPE_ACCOUNT_GROUP);
        AccountGroupOutput accountGroupOutput = new AccountGroupOutput();
        BeanUtils.copyProperties(accountGroup, accountGroupOutput);
        return accountGroupOutput;
    }

    @Override
    @ApiOperation(value = "获取部门数")
    public Integer getAccountGroupsCount() {

        return accountGroupDao.getAccountGroupCount();
    }
}
