package com.kiss.account.validator;

import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.input.*;
import com.kiss.account.status.AccountStatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountGroupValidator implements Validator {

    @Autowired
    private AccountGroupDao accountGroupDao;

    private AccountGroup accountGroup;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateAccountGroupInput.class)
                || clazz.equals(UpdateAccountGroupInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateAccountGroupInput.class.isInstance(target)) {
            CreateAccountGroupInput createAccountGroupInput = (CreateAccountGroupInput) target;
            validateName(null, createAccountGroupInput.getName(), errors);
            validateParentId(createAccountGroupInput.getParentId(), errors);
        } else if (UpdateAccountGroupInput.class.isInstance(target)) {
            UpdateAccountGroupInput updateAccountGroupInput = (UpdateAccountGroupInput) target;
            validateAccountExist(updateAccountGroupInput.getId(), errors);

            if (accountGroup == null) {
                return;
            }

            validateName(updateAccountGroupInput.getId(), updateAccountGroupInput.getName(), errors);
            validateParentId(updateAccountGroupInput.getParentId(), errors);
        } else {
            errors.rejectValue("data", String.valueOf(AccountStatusCode.DATA_BIND_ERROR), "数据绑定错误");
        }

    }

    private void validateAccountExist(Integer id, Errors errors) {

        accountGroup = accountGroupDao.getAccountGroupById(id);

        if (accountGroup == null) {
            errors.rejectValue("id", String.valueOf(AccountStatusCode.ACCOUNT_GROUP_NOT_EXIST), "部门不存在");
        }
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", String.valueOf(AccountStatusCode.ACCOUNT_GROUP_NAME_NOT_EMPTY), "部门名称不能为空");
        }

        AccountGroup findAccountGroup = accountGroupDao.getAccountGroupByName(name);

        if (findAccountGroup == null) {
            return;
        }

        if (id != null && accountGroup.getId().equals(findAccountGroup.getId())) {
            return;
        }

        errors.rejectValue("name", String.valueOf(AccountStatusCode.ACCOUNT_GROUP_NAME_EXIST), "部门名称已存在");
    }


    private void validateParentId(Integer groupId, Errors errors) {

        if (groupId == null) {
            errors.rejectValue("parentId", String.valueOf(AccountStatusCode.ACCOUNT_GROUP_PARENT_NOT_EMPTY), "父部门不能为空");
            return;
        }

        if (groupId.equals(0) || accountGroupDao.getAccountGroupById(groupId) != null) {
            return;
        }

        errors.rejectValue("parentId", String.valueOf(AccountStatusCode.ACCOUNT_GROUP_PARENT_NOT_EXIST), "父部门不存在");
    }

}
