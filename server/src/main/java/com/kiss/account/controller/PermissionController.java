package com.kiss.account.controller;

import com.kiss.account.client.PermissionClient;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.UpdatePermissionInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionOutput;
import com.kiss.account.entity.OperationTargetType;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.validator.PermissionValidator;
import entity.Guest;
import exception.StatusException;
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
import utils.LangUtil;
import utils.ThreadLocalUtil;

import java.util.List;

@RestController
@Api(tags = "Permission", description = "权限相关接口")
public class PermissionController extends BaseController implements PermissionClient {

    @Autowired
    private LangUtil langUtil;

    @Autowired
    private PermissionValidator permissionValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(permissionValidator);
    }

    @Override
    @ApiOperation(value = "创建权限")
    @Transactional
    public PermissionOutput createPermission(@Validated @RequestBody CreatePermissionInput createPermissionInput) throws NoSuchFieldException, IllegalAccessException {

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
//        permissionOutput.setTypeText(DbEnumUtil.getValue("permissions.type", String.valueOf(permissionOutput.getType())));
////        permissionOutput.setStatusText(DbEnumUtil.getValue("permissions.status", String.valueOf(permissionOutput.getStatus())));
        permissionOutput.setTypeText(langUtil.getEnumsMessage("permissions.type", String.valueOf(permissionOutput.getType())));
        permissionOutput.setStatusText(langUtil.getEnumsMessage("permissions.status", String.valueOf(permissionOutput.getStatus())));
        permissionOutput.setModuleName(permissionModule.getName());
        operationLogService.saveOperationLog(guest, null, permission, "id", OperationTargetType.TYPE_PERMISSION);

        return permissionOutput;
    }

    @Override
    @ApiOperation(value = "获取权限列表")
    public List<PermissionOutput> getPermissions() {

        List<PermissionOutput> permissions = permissionDao.getPermissions();

        for (PermissionOutput permissionOutput : permissions) {
            permissionOutput.setStatusText(langUtil.getEnumsMessage("permissions.status", String.valueOf(permissionOutput.getStatus())));
            permissionOutput.setTypeText(langUtil.getEnumsMessage("permissions.type", String.valueOf(permissionOutput.getType())));
        }

        return permissions;
    }

    @Override
    @ApiOperation(value = "获取可以绑定的权限列表")
    public List<BindPermissionOutput> getbindPermissions() {

        List<BindPermissionOutput> bindPermissionOutputs = permissionDao.getBindPermissions();

        return bindPermissionOutputs;
    }

    @Override
    @ApiOperation(value = "更新权限")
    public PermissionOutput updatePermission(@Validated @RequestBody UpdatePermissionInput updatePermissionInput) {

        Guest guest = ThreadLocalUtil.getGuest();
        Permission oldPermission = permissionDao.getPermissionById(updatePermissionInput.getId());
        Permission permission = new Permission();
        BeanUtils.copyProperties(updatePermissionInput, permission);
        Integer count = permissionDao.updatePermission(permission);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.PUT_PERMISSION_FAILD);
        }

        PermissionOutput permissionOutput = new PermissionOutput();
        PermissionModule permissionModule = permissionDao.getPermissionModuleById(updatePermissionInput.getModuleId());
        permissionOutput.setTypeText(langUtil.getEnumsMessage("permissions.type", String.valueOf(permission.getType())));
        permissionOutput.setStatusText(langUtil.getEnumsMessage("permissions.status", String.valueOf(permission.getStatus())));
        permissionOutput.setModuleName(permissionModule.getName());
        operationLogService.saveOperationLog(guest, oldPermission, permission, "id", OperationTargetType.TYPE_PERMISSION);

        return permissionOutput;
    }

    @Override
    @ApiOperation(value = "删除权限")
    public void deletePermission(@RequestParam("id") Integer id) {

        Guest guest = ThreadLocalUtil.getGuest();
        Permission oldPermission = permissionDao.getPermissionById(id);
        Integer count = permissionDao.deletePermissionById(id);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.DELETE_PERMISSION_FAILED);
        }

        operationLogService.saveOperationLog(guest, oldPermission, null, "id", OperationTargetType.TYPE_PERMISSION);
    }

    @Override
    @ApiOperation(value = "获取有效权限数量")
    public Integer getValidPermissionsCount() {

        return permissionDao.getValidPermissionCount();
    }

}
