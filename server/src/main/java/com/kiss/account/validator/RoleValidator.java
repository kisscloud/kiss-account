package com.kiss.account.validator;

import com.kiss.account.dao.RoleDao;
import com.kiss.account.entity.Role;
import com.kiss.account.input.BindPermissionToRoleInput;
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
                || clazz.equals(UpdateRoleInput.class)
                || clazz.equals(BindPermissionToRoleInput.class);
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

        } else if (BindPermissionToRoleInput.class.isInstance(target)) {

            BindPermissionToRoleInput bindPermissionToRoleInput = (BindPermissionToRoleInput) target;
            validateRoleExist(bindPermissionToRoleInput.getRoleId(), errors);

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


}
