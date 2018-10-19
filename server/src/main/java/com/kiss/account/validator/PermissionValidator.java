package com.kiss.account.validator;

import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.utils.ApplicationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PermissionValidator implements Validator {

    private PermissionDao permissionDao;

    private PermissionModule permissionModule;

    public PermissionValidator() {
        permissionDao = (PermissionDao) ApplicationUtil.getBean(PermissionDao.class);
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(UpdatePermissionModuleInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (UpdatePermissionModuleInput.class.isInstance(target)) {

            UpdatePermissionModuleInput updatePermissionModuleInput = (UpdatePermissionModuleInput) target;
            validateModuleExist(updatePermissionModuleInput.getId(), errors);
            validateParentId(updatePermissionModuleInput.getParentId(), errors);
            validateName(updatePermissionModuleInput.getId(), updatePermissionModuleInput.getName(), errors);

        } else {

            errors.rejectValue("", null, "数据格式错误");
        }

    }

    private void validateModuleExist(Integer id, Errors errors) {

        permissionModule = permissionDao.getPermissionModuleById(id);

        if (permissionDao.getPermissionModuleById(id) == null) {
            errors.rejectValue("id", null, "模块不存在");
        }
    }

    private void validateParentId(Integer id, Errors errors) {

        if (id == null) {
            errors.rejectValue("parentId", null, "父模块不能为空");
        }

        if (permissionDao.getPermissionModuleById(id) != null) {
            return;
        }

        errors.rejectValue("parentId", null, "父模块不存在");
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", "9001", "模块名称不能为空");
        }

        PermissionModule findPermissionModule = permissionDao.getPermissionModuleByName(name);

        if (findPermissionModule == null) {
            return;
        }

        if (id != null && permissionModule.getId().equals(findPermissionModule.getId())) {
            return;
        }

        errors.rejectValue("name", "9001", "模块名称已经存在");
    }

}
