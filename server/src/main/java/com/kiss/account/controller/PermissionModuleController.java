package com.kiss.account.controller;

import com.kiss.account.client.PermissionModuleClient;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.validator.PermissionModuleValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Permission", description = "权限相关接口")
public class PermissionModuleController implements PermissionModuleClient {

    @Autowired
    private PermissionDao permissionDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new PermissionModuleValidator());
    }

    /**
     * 创建权限模块
     */
    @Override
    @ApiOperation(value = "创建权限模块")
    public ResultOutput<PermissionModuleOutput> createPermissionModule(@Validated @RequestBody CreatePermissionModuleInput permissionModuleInput) {

        PermissionModule permissionModule = new PermissionModule();
        PermissionModule parentPermissionModule = new PermissionModule();

        // 2. 添加权限模块信息
        permissionModule.setName(permissionModuleInput.getName());
        permissionModule.setParentId(permissionModuleInput.getParentId());
        permissionModule.setSeq(100);
        permissionModule.setRemark(permissionModuleInput.getRemark());
        permissionModule.setLevel("0,");
        permissionModule.setPermissions(0);
        permissionModule.setOperatorId(123);
        permissionModule.setOperatorName("koy");
        permissionModule.setOperatorIp("0.0.0.0");
        permissionModule.setStatus(1);


        permissionDao.createPermissionModule(permissionModule);

        PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
        BeanUtils.copyProperties(permissionModule, permissionModuleOutput);

        return ResultOutputUtil.success(permissionModuleOutput);
    }

    @Override
    @ApiOperation(value = "获取权限模块列表")
    public ResultOutput<List<PermissionModuleOutput>> getPermissionModules() {

        List<PermissionModule> permissionModules = permissionDao.getPermissionModules();
        List<PermissionModuleOutput> permissionModuleOutputs = new ArrayList<>();

        for (PermissionModule permissionModule : permissionModules) {
            PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
            BeanUtils.copyProperties(permissionModule, permissionModuleOutput);
            permissionModuleOutputs.add(permissionModuleOutput);
        }

        return ResultOutputUtil.success(permissionModuleOutputs);
    }

    @Override
    @ApiOperation(value = "获取权限模块列表")
    public ResultOutput<List<PermissionModuleOutput>> getBindPermissionModules() {

        List<PermissionModule> permissionModules = permissionDao.getBindPermissionModules();
        List<PermissionModuleOutput> permissionModuleOutputs = new ArrayList<>();

        for (PermissionModule permissionModule : permissionModules) {
            PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
            BeanUtils.copyProperties(permissionModule, permissionModuleOutput);
            permissionModuleOutputs.add(permissionModuleOutput);
        }

        return ResultOutputUtil.success(permissionModuleOutputs);
    }

    @Override
    @ApiOperation(value = "更新权限模块")
    public ResultOutput<PermissionModuleOutput> updatePermissionModule(@Validated @RequestBody UpdatePermissionModuleInput updatePermissionModuleInput) {

        PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
        BeanUtils.copyProperties(updatePermissionModuleInput, permissionModuleOutput);
        Integer count = permissionDao.updatePermissionModule(permissionModuleOutput);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.PUT_PERMISSION_MODULE_FAILD);
        }

        return ResultOutputUtil.success(permissionModuleOutput);
    }

    @Override
    @ApiOperation(value = "删除权限模块")
    public ResultOutput deletePermissionModule(@RequestParam("id") Integer id) {

        List<Permission> permissions = permissionDao.getPermissionByModuleId(id);

        if (permissions != null && permissions.size() != 0) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_IS_NOT_EMPTY);
        }

        List<PermissionModule> permissionModules = permissionDao.getPermissionModuleChildren(id);

        if (permissionModules != null && permissionModules.size() != 0) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_IS_NOT_EMPTY);
        }

        Integer count = permissionDao.deletePermissionModule(id);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.DELETE_PERMISSION_MODULE_FAILED);
        }

        return ResultOutputUtil.success();
    }
}
