package com.kiss.account.validator;

import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.status.AccountStatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PermissionModuleValidator implements Validator {

    @Autowired
    private PermissionDao permissionDao;

    private PermissionModule permissionModule;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreatePermissionModuleInput.class)
                || clazz.equals(UpdatePermissionModuleInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreatePermissionModuleInput.class.isInstance(target)) {
            CreatePermissionModuleInput createPermissionModuleInput = (CreatePermissionModuleInput) target;
            validateParentId(createPermissionModuleInput.getParentId(), errors);
            validateName(null, createPermissionModuleInput.getName(), errors);
        } else if (UpdatePermissionModuleInput.class.isInstance(target)) {
            UpdatePermissionModuleInput updatePermissionModuleInput = (UpdatePermissionModuleInput) target;
            validateModuleExist(updatePermissionModuleInput.getId(), errors);

            if (permissionModule == null) {
                return;
            }

            validateParentId(updatePermissionModuleInput.getParentId(), errors);
            validateName(updatePermissionModuleInput.getId(), updatePermissionModuleInput.getName(), errors);
        } else {
            errors.rejectValue("", null, "数据格式错误");
        }

    }

    private void validateModuleExist(Integer id, Errors errors) {

        permissionModule = permissionDao.getPermissionModuleById(id);

        if (permissionDao.getPermissionModuleById(id) == null) {
            errors.rejectValue("id", String.valueOf(AccountStatusCode.PERMISSION_MODULE_NOT_EXIST), "模块不存在");
        }
    }

    private void validateParentId(Integer parentId, Errors errors) {

        if (parentId == null) {
            errors.rejectValue("parentId", String.valueOf(AccountStatusCode.PERMISSION_MODULE_PARENT_NOT_EMPTY), "父模块不能为空");
            return;
        }

        if (parentId.equals(0) || permissionDao.getPermissionModuleById(parentId) != null) {
            return;
        }

        errors.rejectValue("parentId", String.valueOf(AccountStatusCode.PERMISSION_MODULE_PARENT_NOT_EXIST), "父模块不存在");
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", String.valueOf(AccountStatusCode.PERMISSION_MODULE_NAME_NOT_EMPTY), "模块名称不能为空");
        }

        PermissionModule findPermissionModule = permissionDao.getPermissionModuleByName(name);

        if (findPermissionModule == null) {
            return;
        }

        if (id != null && permissionModule.getId().equals(findPermissionModule.getId())) {
            return;
        }

        errors.rejectValue("name", String.valueOf(AccountStatusCode.PERMISSION_MODULE_NAME_EXIST), "模块名称已经存在");
    }

}
