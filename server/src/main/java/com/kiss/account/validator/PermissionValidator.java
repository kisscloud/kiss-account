package com.kiss.account.validator;

import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.UpdatePermissionInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.utils.ApplicationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PermissionValidator implements Validator {

    private PermissionDao permissionDao;

    public PermissionValidator() {
        permissionDao = (PermissionDao) ApplicationUtil.getBean(PermissionDao.class);
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreatePermissionInput.class)
                || clazz.equals(UpdatePermissionInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreatePermissionInput.class.isInstance(target)) {

            CreatePermissionInput createPermissionInput = (CreatePermissionInput) target;

        } else if (UpdatePermissionInput.class.isInstance(target)) {

            UpdatePermissionInput updatePermissionInput = (UpdatePermissionInput) target;


        } else {

            errors.rejectValue("data", "", "数据格式错误");
        }

    }


}
