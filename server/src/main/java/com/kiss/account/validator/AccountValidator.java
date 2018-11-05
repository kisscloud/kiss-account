package com.kiss.account.validator;

import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.input.UpdateAccountInput;
import com.kiss.account.input.UpdateAccountStatusInput;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import com.kiss.account.input.ValidateAccountInput;
import com.kiss.account.status.AccountStatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountGroupDao accountGroupDao;

    private Account account;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateAccountInput.class)
                || clazz.equals(UpdateAccountInput.class)
                || clazz.equals(UpdateAccountStatusInput.class)
                || clazz.equals(ValidateAccountInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateAccountInput.class.isInstance(target)) {
            CreateAccountInput createAccountInput = (CreateAccountInput) target;
            validateName(null, createAccountInput.getName(), errors);
            validateUsername(null, createAccountInput.getUsername(), errors);
            validateEmail(null, createAccountInput.getEmail(), errors);
            validateMobile(null, createAccountInput.getMobile(), errors);
            validateGroupId(createAccountInput.getGroupId(), errors);
            validatePassword(createAccountInput.getPassword(), errors);
        } else if (UpdateAccountInput.class.isInstance(target)) {
            UpdateAccountInput updateAccountInput = (UpdateAccountInput) target;
            validateAccountExist(updateAccountInput.getId(), errors);

            if (account == null) {
                return;
            }

            validateName(updateAccountInput.getId(), updateAccountInput.getName(), errors);
            validateUsername(updateAccountInput.getId(), updateAccountInput.getUsername(), errors);
            validateEmail(updateAccountInput.getId(), updateAccountInput.getEmail(), errors);
            validateMobile(updateAccountInput.getId(), updateAccountInput.getMobile(), errors);
            validateGroupId(updateAccountInput.getGroupId(), errors);
        } else if (UpdateAccountStatusInput.class.isInstance(target)) {
            UpdateAccountStatusInput updateAccountStatusInput = (UpdateAccountStatusInput) target;
            validateStatus(updateAccountStatusInput.getStatus(), errors);
        } else if (ValidateAccountInput.class.isInstance(target)) {
            ValidateAccountInput validateAccountInput = (ValidateAccountInput) target;
            validateId(validateAccountInput.getId(),errors);
            validatePassword(validateAccountInput.getPassword(),errors);
        } else {
            errors.rejectValue("data", String.valueOf(AccountStatusCode.DATA_BIND_ERROR), "数据绑定错误");
        }

    }

    private void validateAccountExist(Integer id, Errors errors) {

        account = accountDao.getAccountById(id);

        if (account == null) {
            errors.rejectValue("id", String.valueOf(AccountStatusCode.ACCOUNT_NOT_EXIST), "账户不存在");
        }
    }

    private void validateUsername(Integer id, String username, Errors errors) {

        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("username", String.valueOf(AccountStatusCode.ACCOUNT_USERNAME_NOT_EMPTY), "用户名不能为空");
        }

        Account findAccount = accountDao.getAccountByUsername(username);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("username", String.valueOf(AccountStatusCode.ACCOUNT_USERNAME_EXIST), "用户名已存在");
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {

            errors.rejectValue("name", String.valueOf(AccountStatusCode.ACCOUNT_NAME_NOT_EMPTY), "姓名不能为空");
        }

        Account findAccount = accountDao.getAccountByName(name);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("name", String.valueOf(AccountStatusCode.ACCOUNT_NAME_EXIST), "姓名已存在");
    }

    private void validateMobile(Integer id, String mobile, Errors errors) {

        if (StringUtils.isEmpty(mobile)) {

            errors.rejectValue("mobile", String.valueOf(AccountStatusCode.ACCOUNT_MOBILE_NOT_EMPTY), "手机号不能为空");
        }

        Account findAccount = accountDao.getAccountByMobile(mobile);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("mobile", String.valueOf(AccountStatusCode.ACCOUNT_MOBILE_EXIST), "手机号已存在");
    }

    private void validateEmail(Integer id, String email, Errors errors) {

        if (StringUtils.isEmpty(email)) {

            errors.rejectValue("email", String.valueOf(AccountStatusCode.ACCOUNT_EMAIL_NOT_EMPTY), "邮箱不能为空");
        }

        Account findAccount = accountDao.getAccountByEmail(email);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("email", String.valueOf(AccountStatusCode.ACCOUNT_EMAIL_EXIST), "邮箱已存在");
    }

    private void validateGroupId(Integer groupId, Errors errors) {

        if (groupId == null) {
            errors.rejectValue("groupId", String.valueOf(AccountStatusCode.ACCOUNT_GROUPID_NOT_EMPTY), "部门不能为空");
        }

        if (accountGroupDao.getAccountGroupById(groupId) == null) {
            errors.rejectValue("groupId", String.valueOf(AccountStatusCode.ACCOUNT_GROUP_NOT_EXIST), "部门不存在");
        }
    }

    private void validatePassword(String password, Errors errors) {

        if (StringUtils.isEmpty(password)) {
            errors.rejectValue("password", String.valueOf(AccountStatusCode.ACCOUNT_PASSWORD_NOT_EMPTY), "密码不能为空");
        }

        if (password.length() < 8) {
            errors.rejectValue("password", String.valueOf(AccountStatusCode.ACCOUNT_PASSWORD_SIZE_NOT_LESS_THAN_EIGHT), "密码不能小于8位");
        }

        if (password.length() > 32) {
            errors.rejectValue("password", String.valueOf(AccountStatusCode.ACCOUNT_PASSWORD_SIZE_NOT_MORE_THAN_THIRTY_TWO), "密码不能大于32位");
        }
    }

    private void validateStatus(Integer status, Errors errors) {

        if (status == null) {
            errors.rejectValue("status", String.valueOf(AccountStatusCode.ACCOUNT_STATUS_NOT_EMPTY), "用户状态不能为空");
        }
    }

    public void validateId(Integer id,Errors errors) {

        if (id == null) {
            errors.rejectValue("id",String.valueOf(AccountStatusCode.ACCOUNT_ID_NOT_EMPTY),"用户id不能为空");
        }
    }
}
