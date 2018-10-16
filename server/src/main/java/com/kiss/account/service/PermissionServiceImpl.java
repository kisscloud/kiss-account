package com.kiss.account.service;

import com.kiss.account.client.PermissionClient;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.PutPermissionInput;
import com.kiss.account.input.PutPermissionModuleInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Transactional
    public ResultOutput<PermissionOutput> postPermissions(@Validated @RequestBody CreatePermissionInput createPermissionInput) {

        // 1. 查询权限所属模块信息
        PermissionModule permissionModule = permissionDao.getPermissionModuleById(createPermissionInput.getModuleId());
        if (permissionModule == null) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_NOT_EXIST);
        }

        // 2. 添加权限信息
        Permission permission = new Permission();
        permission.setName(createPermissionInput.getName());
        permission.setModuleId(createPermissionInput.getModuleId());
        permission.setType(createPermissionInput.getType());
        permission.setCode(createPermissionInput.getCode());
        // permission.setDataFields();
        permission.setStatus(createPermissionInput.getStatus());
        permission.setSeq(10);
        permission.setRemark(createPermissionInput.getRemark());
        permission.setOperatorId(0);
        permission.setOperatorName("system");
        permission.setOperatorIp("0.0.0.0");
        Permission exist = permissionDao.getPermissionByNameOrCode(permission);

        if (exist != null) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_EXIST);
        }

        permissionDao.createPermission(permission);

        // 3. 更新权限所属模块的权限数
        permissionDao.putPermissionModulePermissionsCount(permission.getModuleId(), +1);

        // 4. 更新权限所属模块父模块权限数
        String[] modulesIds = permissionModule.getLevel().split(",");
        for (String moduleIdString : modulesIds) {
            permissionDao.putPermissionModulePermissionsCount(Integer.parseInt(moduleIdString), +1);
        }


        PermissionOutput permissionOutput = new PermissionOutput();
        BeanUtils.copyProperties(permission, permissionOutput);
        return ResultOutputUtil.success(permissionOutput);
    }

    /**
     * 创建权限模块
     */
    @Override
    @ApiOperation(value = "创建权限模块")
    public ResultOutput<PermissionModuleOutput> postPermissionsModules(@Validated @RequestBody CreatePermissionModuleInput permissionModuleInput) {

        PermissionModule permissionModule = permissionDao.getPermissionModuleByName(permissionModuleInput.getName());

        if (permissionModule != null) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_EXIST);
        }

        permissionModule = new PermissionModule();
        PermissionModule parentPermissionModule = new PermissionModule();

        // 1. 如果父模块ID不等于0，则查询父模块信息；
        //    * 绑定权限模块 level， 等于父权限模块的 level 拼接父权限模块的 id；
        if (permissionModuleInput.getParentId() != 0) {
            parentPermissionModule = permissionDao.getPermissionModuleById(permissionModuleInput.getParentId());
            if (parentPermissionModule == null) {
                return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_NOT_EXIST);
            }
            permissionModule.setLevel(String.format("%s,%d", parentPermissionModule.getLevel(), parentPermissionModule.getId()));
        }

        // 2. 添加权限模块信息
        permissionModule.setName(permissionModuleInput.getName());
        permissionModule.setParentId(permissionModuleInput.getParentId());
        permissionModule.setSeq(100);
        permissionModule.setRemark(permissionModuleInput.getRemark());
        permissionModule.setPermissions(0);
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

    @Override
    public ResultOutput<List<PermissionModuleOutput>> getBindPermissionsModules() {

        List<PermissionModule> permissionModules = permissionDao.getBindPermissionsModules();
        List<PermissionModuleOutput> permissionModuleOutputs = new ArrayList<>();

        for (PermissionModule permissionModule : permissionModules) {
            PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
            BeanUtils.copyProperties(permissionModule, permissionModuleOutput);
            permissionModuleOutputs.add(permissionModuleOutput);
        }

        return ResultOutputUtil.success(permissionModuleOutputs);
    }

    @Override
    public ResultOutput<PermissionOutput> putPermission(@Validated @RequestBody PutPermissionInput putPermissionInput) {
        Permission permission = new Permission();
        permission.setCode(putPermissionInput.getCode());
        permission.setName(putPermissionInput.getName());
        Permission exist = permissionDao.getPermissionByNameOrCode(permission);

        if(exist != null) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_EXIST);
        }

        PermissionOutput permissionOutput = new PermissionOutput();
        BeanUtils.copyProperties(putPermissionInput,permissionOutput);
        Integer count = permissionDao.putPermission(permissionOutput);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_PERMISSION_FAILD);
        }

        return ResultOutputUtil.success(permissionOutput);
    }

    @Override
    public ResultOutput<PermissionModuleOutput> putPermissionModule(@Validated @RequestBody PutPermissionModuleInput putPermissionModuleInput) {
        PermissionModule permissionModule = permissionDao.getPermissionModuleByName(putPermissionModuleInput.getName());

        if (permissionModule != null) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_EXIST);
        }

        PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
        BeanUtils.copyProperties(putPermissionModuleInput,permissionModuleOutput);
        Integer count = permissionDao.putPermissionModule(permissionModuleOutput);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_PERMISSION_MODULE_FAILD);
        }

        return ResultOutputUtil.success(permissionModuleOutput);
    }

    @Override
    public ResultOutput deletePermission(@RequestParam("id") Integer id) {
        Integer count = permissionDao.deletePermission(id);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.DELETE_PERMISSION_FAILED);
        }

        return ResultOutputUtil.success();
    }

    @Override
    public ResultOutput deletePermissionModule(@RequestParam("id") Integer id) {

        List<Permission> permissions = permissionDao.getPermissionByModuleId(id);

        if(permissions != null && permissions.size() != 0) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_IS_NOT_EMPTY);
        }

        Integer count = permissionDao.deletePermissionModule(id);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.DELETE_PERMISSION_MODULE_FAILED);
        }

        return ResultOutputUtil.success();
    }
}
