package com.kiss.account.controller;

import com.kiss.account.client.PermissionModuleClient;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Guest;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.service.OperationLogService;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.utils.ThreadLocalUtil;
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

    @Autowired
    private OperationLogService operationLogService;

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

        Guest guest = ThreadLocalUtil.getGuest();
        PermissionModule permissionModule = new PermissionModule();
        permissionModule.setName(permissionModuleInput.getName());
        permissionModule.setParentId(permissionModuleInput.getParentId());
        permissionModule.setSeq(100);
        permissionModule.setRemark(permissionModuleInput.getRemark());
        permissionModule.setLevel("0,");
        permissionModule.setPermissions(0);
        permissionModule.setOperatorId(guest.getId());
        permissionModule.setOperatorName(guest.getName());
        permissionModule.setOperatorIp(guest.getIp());
        permissionModule.setStatus(1);
        permissionDao.createPermissionModule(permissionModule);
        PermissionModuleOutput permissionModuleOutput = new PermissionModuleOutput();
        BeanUtils.copyProperties(permissionModule, permissionModuleOutput);
        operationLogService.savePermissionModuleLog(guest, null, permissionModule);

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

        Guest guest = ThreadLocalUtil.getGuest();
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

        List<PermissionModule> permissionModules = permissionDao.getPermissionModuleChildrenByParentId(id);

        if (permissionModules != null && permissionModules.size() != 0) {
            return ResultOutputUtil.error(AccountStatusCode.PERMISSION_MODULE_IS_NOT_EMPTY);
        }

        Guest guest = ThreadLocalUtil.getGuest();
        PermissionModule oldValue = permissionDao.getPermissionModuleById(id);
        Integer count = permissionDao.deletePermissionModuleById(id);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.DELETE_PERMISSION_MODULE_FAILED);
        }

        operationLogService.savePermissionModuleLog(guest, oldValue, null);

        return ResultOutputUtil.success();
    }
}
