package com.kiss.account.validator;

import com.kiss.account.dao.PermissionDao;
import com.kiss.account.dao.RoleDao;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreateRoleInput;
import com.kiss.account.input.UpdatePermissionInput;
import com.kiss.account.input.UpdateRoleInput;
import com.kiss.account.utils.ApplicationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoleValidator implements Validator {

    private RoleDao roleDao;

    public RoleValidator() {
        roleDao = (RoleDao) ApplicationUtil.getBean(RoleDao.class);
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateRoleInput.class)
                || clazz.equals(UpdateRoleInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateRoleInput.class.isInstance(target)) {

            CreateRoleInput createRoleInput = (CreateRoleInput) target;

        } else if (UpdateRoleInput.class.isInstance(target)) {

            UpdateRoleInput updateRoleInput = (UpdateRoleInput) target;


        } else {

            errors.rejectValue("data", "", "数据格式错误");
        }

    }


}
