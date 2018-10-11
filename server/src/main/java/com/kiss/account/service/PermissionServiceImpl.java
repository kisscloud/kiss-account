package com.kiss.account.service;

import com.kiss.account.client.PermissionClient;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.output.ResultOutput;
import com.kiss.account.utils.ResultOutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PermissionServiceImpl implements PermissionClient {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public ResultOutput create(CreatePermissionInput createPermissionInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultOutputUtil.validateError(bindingResult.getFieldError().getDefaultMessage());
        }

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

        return ResultOutputUtil.success(permission);
    }

    @Override
    public ResultOutput createModule(CreatePermissionModuleInput permissionModuleInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultOutputUtil.validateError(bindingResult.getFieldError().getDefaultMessage());
        }

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
        return ResultOutputUtil.success(permissionModule);
    }

    @Override
    public String list() {
        return null;
    }

    @Override
    public String find() {
        return null;
    }
}
