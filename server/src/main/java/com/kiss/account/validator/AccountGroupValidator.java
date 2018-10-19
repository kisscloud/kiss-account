package com.kiss.account.validator;

import com.kiss.account.dao.AccountDao;
import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.input.*;
import com.kiss.account.utils.ApplicationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountGroupValidator implements Validator {

    private AccountGroupDao accountGroupDao;

    private AccountGroup accountGroup;

    public AccountGroupValidator() {
        accountGroupDao = (AccountGroupDao) ApplicationUtil.getBean(AccountGroupDao.class);
    }

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
            validateName(updateAccountGroupInput.getId(), updateAccountGroupInput.getName(), errors);
            validateParentId(updateAccountGroupInput.getParentId(), errors);

        } else {

            errors.rejectValue("data", "", "数据格式错误");
        }

    }

    private void validateAccountExist(Integer id, Errors errors) {

        accountGroup = accountGroupDao.getAccountGroupById(id);

        if (accountGroup == null) {
            errors.rejectValue("id", "", "模块不存在");
        }
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", "", "模块名称不能为空");
        }

        AccountGroup findAccountGroup = accountGroupDao.getAccountGroupByName(name);

        if (findAccountGroup == null) {
            return;
        }

        if (id != null && accountGroup.getId().equals(findAccountGroup.getId())) {
            return;
        }

        errors.rejectValue("name", "", "模块名称已存在");
    }


    private void validateParentId(Integer groupId, Errors errors) {

        if (groupId == null) {
            errors.rejectValue("parentId", "", "父模块不能为空");
        }

        if (accountGroupDao.getAccountGroupById(groupId) == null) {
            errors.rejectValue("parentId", "", "父模块不存在");
        }
    }

}
