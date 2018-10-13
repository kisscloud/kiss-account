package com.kiss.account.service;

import com.kiss.account.client.PermissionClient;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Permission", description = "权限相关接口")
public class PermissionServiceImpl implements PermissionClient {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @ApiOperation(value = "创建权限")
    public ResultOutput<PermissionOutput> postPermissions(@Validated @RequestBody CreatePermissionInput createPermissionInput) {

        Permission permission = new Permission();

        permission.setName(createPermissionInput.getName());
        permission.setModuleId(createPermissionInput.getModuleId());
        permission.setType(createPermissionInput.getType());
        permission.setCode(createPermissionInput.getCode());
//        permission.setDataFields();
        permission.setStatus(createPermissionInput.getStatus());
        permission.setSeq(10);
        permission.setRemark(createPermissionInput.getRemark());
        permission.setOperatorId(0);
        permission.setOperatorName("system");
        permission.setOperatorIp("0.0.0.0");
        permissionDao.createPermission(permission);

        PermissionOutput permissionOutput = new PermissionOutput();
        BeanUtils.copyProperties(permission, permissionOutput);
        return ResultOutputUtil.success(permissionOutput);
    }

    @Override
    @ApiOperation(value = "创建权限模块")
    public ResultOutput<PermissionModuleOutput> postPermissionsModules(@Validated @RequestBody CreatePermissionModuleInput permissionModuleInput) {

        PermissionModule permissionModule = new PermissionModule();
        permissionModule.setName(permissionModuleInput.getName());
        permissionModule.setParentId(permissionModuleInput.getParentId());
        permissionModule.setLevel("");
        permissionModule.setSeq(100);
        permissionModule.setRemark(permissionModuleInput.getRemark());
        permissionModule.setOperatorId(0);
        permissionModule.setOperatorName("System");
        permissionModule.setOperatorIp("0.0.0.0");
        permissionModule.setStatus(1);
        permissionDao.createPermissionModule(permissionModule);

        PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
        BeanUtils.copyProperties(permissionModule, permissionModuleOutput);
        return ResultOutputUtil.success(permissionModuleOutput);
    }

    @Override
    @ApiOperation(value = "获取权限列表")
    public ResultOutput<List<PermissionOutput>> getPermissions() {

        List<PermissionOutput> permissions = permissionDao.getPermissions();

        return ResultOutputUtil.success(permissions);
    }

    @Override
    @ApiOperation(value = "获取权限详情")
    public ResultOutput<PermissionOutput> getPermission() {
        return null;
    }

    @Override
    @ApiOperation(value = "获取可以绑定的权限列表")
    public ResultOutput<List<BindPermissionOutput>> getBindPermissions() {

        List<BindPermissionOutput> bindPermissionOutputs = permissionDao.getBindPermissions();

        return ResultOutputUtil.success(bindPermissionOutputs);
    }

    @Override
    public ResultOutput<List<PermissionModuleOutput>> getPermissionsModules() {

        List<PermissionModule> permissionModules = permissionDao.getPermissionsModules();
        List<PermissionModuleOutput> permissionModuleOutputs = new ArrayList<>();

        for (PermissionModule permissionModule : permissionModules) {
            PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
            BeanUtils.copyProperties(permissionModule, permissionModuleOutput);
            permissionModuleOutputs.add(permissionModuleOutput);
        }

        return ResultOutputUtil.success(permissionModuleOutputs);
    }
}
