package com.kiss.account.controller;

import com.kiss.account.client.PermissionClient;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Guest;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.UpdatePermissionInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionOutput;
import com.kiss.account.service.OperationLogService;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.DbEnumUtil;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.utils.ThreadLocalUtil;
import com.kiss.account.validator.PermissionModuleValidator;
import com.kiss.account.validator.PermissionValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import java.util.List;

@RestController
@Api(tags = "Permission", description = "权限相关接口")
public class PermissionController implements PermissionClient {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private OperationLogService operationLogService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(permissionValidator);
    }

    @Override
    @ApiOperation(value = "创建权限")
    @Transactional
    public ResultOutput<PermissionOutput> createPermission(@Validated @RequestBody CreatePermissionInput createPermissionInput) {

        Guest guest = ThreadLocalUtil.getGuest();

        // 1. 查询权限所属模块信息
        PermissionModule permissionModule = permissionDao.getPermissionModuleById(createPermissionInput.getModuleId());

        // 2. 添加权限信息
        Permission permission = new Permission();
        permission.setName(createPermissionInput.getName());
        permission.setModuleId(createPermissionInput.getModuleId());
        permission.setType(createPermissionInput.getType());
        permission.setCode(createPermissionInput.getCode());
        permission.setLimitFields(createPermissionInput.getLimitFields());
        permission.setStatus(createPermissionInput.getStatus());
        permission.setSeq(10);
        permission.setRemark(createPermissionInput.getRemark());
        permission.setOperatorId(guest.getId());
        permission.setOperatorName(guest.getName());
        permission.setOperatorIp(guest.getIp());

        permissionDao.createPermission(permission);

        // 3. 更新权限所属模块的权限数
        permissionDao.updatePermissionModulePermissionsCount(permission.getModuleId(), +1);

        // 4. 更新权限所属模块父模块权限数
        String[] modulesIds = permissionModule.getLevel().split(",");
        for (String moduleIdString : modulesIds) {
            if (!moduleIdString.equals("")) {
                permissionDao.updatePermissionModulePermissionsCount(Integer.parseInt(moduleIdString), +1);
            }
        }

        PermissionOutput permissionOutput = new PermissionOutput();
        BeanUtils.copyProperties(permission, permissionOutput);

        permissionOutput.setTypeText(DbEnumUtil.getValue("permissions.type", String.valueOf(permissionOutput.getType())));
        permissionOutput.setStatusText(DbEnumUtil.getValue("permissions.status", String.valueOf(permissionOutput.getStatus())));
        permissionOutput.setModuleName(permissionModule.getName());


        operationLogService.savePermissionLog(guest, null, permission);

        return ResultOutputUtil.success(permissionOutput);
    }

    @Override
    @ApiOperation(value = "获取权限列表")
    public ResultOutput<List<PermissionOutput>> getPermissions() {

        List<PermissionOutput> permissions = permissionDao.getPermissions();
        for (PermissionOutput permissionOutput : permissions) {
            permissionOutput.setStatusText(DbEnumUtil.getValue("permissions.status", String.valueOf(permissionOutput.getStatus())));
            permissionOutput.setTypeText(DbEnumUtil.getValue("permissions.type", String.valueOf(permissionOutput.getType())));
        }

        return ResultOutputUtil.success(permissions);
    }

    @Override
    @ApiOperation(value = "获取权限详情")
    public ResultOutput<PermissionOutput> getPermission() {
        return null;
    }

    @Override
    @ApiOperation(value = "获取可以绑定的权限列表")
    public ResultOutput<List<BindPermissionOutput>> getbindPermissions() {

        List<BindPermissionOutput> bindPermissionOutputs = permissionDao.getBindPermissions();

        return ResultOutputUtil.success(bindPermissionOutputs);
    }

    @Override
    @ApiOperation(value = "更新权限")
    public ResultOutput<PermissionOutput> updatePermission(@Validated @RequestBody UpdatePermissionInput updatePermissionInput) {

        Guest guest = ThreadLocalUtil.getGuest();

        PermissionOutput oldPermissionOutput = permissionDao.getPermissionById(updatePermissionInput.getId());
        Permission oldValue = new Permission();
        Permission newValue = new Permission();
        BeanUtils.copyProperties(oldPermissionOutput, oldValue);


        PermissionOutput newPermissionOutput = new PermissionOutput();
        BeanUtils.copyProperties(updatePermissionInput, newPermissionOutput);
        Integer count = permissionDao.updatePermission(newPermissionOutput);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_PERMISSION_FAILD);
        }

        PermissionModule permissionModule = permissionDao.getPermissionModuleById(updatePermissionInput.getModuleId());
        newPermissionOutput.setTypeText(DbEnumUtil.getValue("permissions.type", String.valueOf(newPermissionOutput.getType())));
        newPermissionOutput.setStatusText(DbEnumUtil.getValue("permissions.status", String.valueOf(newPermissionOutput.getStatus())));
        newPermissionOutput.setModuleName(permissionModule.getName());

        BeanUtils.copyProperties(newPermissionOutput, newValue);

        operationLogService.savePermissionLog(guest, oldValue, newValue);

        return ResultOutputUtil.success(newPermissionOutput);
    }

    @Override
    @ApiOperation(value = "删除权限")
    public ResultOutput deletePermission(@RequestParam("id") Integer id) {

        Guest guest = ThreadLocalUtil.getGuest();

        PermissionOutput oldPermissionOutput = permissionDao.getPermissionById(id);
        Permission oldValue = new Permission();
        BeanUtils.copyProperties(oldPermissionOutput, oldValue);

        Integer count = permissionDao.deletePermission(id);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.DELETE_PERMISSION_FAILED);
        }

        operationLogService.savePermissionLog(guest, oldValue, null);

        return ResultOutputUtil.success();
    }

}
