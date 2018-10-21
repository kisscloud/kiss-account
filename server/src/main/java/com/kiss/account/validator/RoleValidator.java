package com.kiss.account.validator;

import com.kiss.account.dao.RoleDao;
import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermission;
import com.kiss.account.input.BindPermissionToRoleInput;
import com.kiss.account.input.BindRoleDataPermissions;
import com.kiss.account.input.CreateRoleInput;
import com.kiss.account.input.UpdateRoleInput;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {

    @Autowired
    private RoleDao roleDao;

    private Role role;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateRoleInput.class)
                || clazz.equals(UpdateRoleInput.class) || clazz.equals(BindRoleDataPermissions.class) || clazz.equals(BindPermissionToRoleInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateRoleInput.class.isInstance(target)) {

            CreateRoleInput createRoleInput = (CreateRoleInput) target;
            validateName(null, createRoleInput.getName(), errors);

        } else if (UpdateRoleInput.class.isInstance(target)) {

            UpdateRoleInput updateRoleInput = (UpdateRoleInput) target;
            validateRoleExist(updateRoleInput.getId(), errors);

            if (role == null) {
                return;
            }

            validateName(updateRoleInput.getId(), updateRoleInput.getName(), errors);

        } else if (BindRoleDataPermissions.class.isInstance(target)) {
            BindRoleDataPermissions bindRoleDataPermissions = (BindRoleDataPermissions) target;
            validateRolePermissionExist(bindRoleDataPermissions.getRoleId(),bindRoleDataPermissions.getPermissionId(),errors);
        } else if (BindPermissionToRoleInput.class.isInstance(target)) {

        } else {

            errors.rejectValue("data", "", "数据格式错误");
        }

    }

    private void validateRoleExist(Integer id, Errors errors) {

        role = roleDao.getRoleById(id);

        if (role == null) {
            errors.rejectValue("id", "", "角色不存在");
        }
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {

            errors.rejectValue("name", "", "角色名不能为空");
        }

        Role findRole = roleDao.getRoleByName(name);

        if (findRole == null) {
            return;
        }

        if (id != null && role.getId().equals(findRole.getId())) {
            return;
        }

        errors.rejectValue("name", "", "角色名已存在");
    }

    private void validateRolePermissionExist(Integer roleId,Integer permissionId,Errors errors) {

        if (roleId == null) {
            errors.rejectValue("roleId", "", "角色id不能为空");
            return;
        }

        if (permissionId == null) {
            errors.rejectValue("permissionId", "", "权限id不能为空");
            return;
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        RolePermission exist = roleDao.getRolePermission(rolePermission);
        if (exist == null) {
            errors.rejectValue("permissionId","","角色权限不存在");
        }
    }
}
