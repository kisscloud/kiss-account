package com.kiss.account.validator;

import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.input.UpdateAccountInput;
import com.kiss.account.input.UpdateAccountStatusInput;
import com.kiss.account.utils.ApplicationUtil;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountValidator implements Validator {

    private AccountDao accountDao;

    private AccountGroupDao accountGroupDao;

    private Account account;

    public AccountValidator() {
        accountDao = (AccountDao) ApplicationUtil.getBean(AccountDao.class);
        accountGroupDao = (AccountGroupDao) ApplicationUtil.getBean(AccountGroupDao.class);
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateAccountInput.class)
                || clazz.equals(UpdateAccountInput.class)
                || clazz.equals(UpdateAccountStatusInput.class);
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
            validateName(updateAccountInput.getId(), updateAccountInput.getName(), errors);
            validateUsername(updateAccountInput.getId(), updateAccountInput.getUsername(), errors);
            validateEmail(updateAccountInput.getId(), updateAccountInput.getEmail(), errors);
            validateMobile(updateAccountInput.getId(), updateAccountInput.getMobile(), errors);

            validateGroupId(updateAccountInput.getGroupId(), errors);

        } else if (UpdateAccountStatusInput.class.isInstance(target)) {

            UpdateAccountStatusInput updateAccountStatusInput = (UpdateAccountStatusInput) target;
            validateStatus(updateAccountStatusInput.getStatus(), errors);

        } else {

            errors.rejectValue("", "", "数据格式错误");
        }

    }

    private void validateAccountExist(Integer id, Errors errors) {

        account = accountDao.getAccountById(id);

        if (account == null) {
            errors.rejectValue("id", "", "账户不存在");
        }
    }

    private void validateUsername(Integer id, String username, Errors errors) {

        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("name", "9001", "用户名不能为空");
        }

        Account findAccount = accountDao.getAccountByUsername(username);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("name", "9001", "用户名已存在");
    }

    private void validateName(Integer id, String name, Errors errors) {

        if (StringUtils.isEmpty(name)) {

            errors.rejectValue("name", "9001", "姓名不能为空");
        }

        Account findAccount = accountDao.getAccountByName(name);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("name", "9002", "姓名已存在");
    }

    private void validateMobile(Integer id, String mobile, Errors errors) {

        if (StringUtils.isEmpty(mobile)) {

            errors.rejectValue("name", "9001", "手机号不能为空");
        }

        Account findAccount = accountDao.getAccountByMobile(mobile);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }

        errors.rejectValue("name", "9003", "手机号已存在");
    }

    private void validateEmail(Integer id, String email, Errors errors) {

        if (StringUtils.isEmpty(email)) {

            errors.rejectValue("name", "9001", "邮箱不能为空");
        }

        Account findAccount = accountDao.getAccountByEmail(email);

        if (findAccount == null) {
            return;
        }

        if (id != null && account.getId().equals(findAccount.getId())) {
            return;
        }


        errors.rejectValue("name", "9004", "邮箱已存在");
    }

    private void validateGroupId(Integer groupId, Errors errors) {
        if (groupId == null) {
            errors.rejectValue("name", "9001", "部门不能为空");
        }

        if (accountGroupDao.getAccountGroupById(groupId) == null) {
            errors.rejectValue("name", "9001", "部门不存在");
        }
    }

    private void validatePassword(String password, Errors errors) {
        if (StringUtils.isEmpty(password)) {
            errors.rejectValue("name", "9001", "密码不能为空");
        }
        if (password.length() < 8) {
            errors.rejectValue("name", "9001", "密码不能小于8位");
        }
        if (password.length() > 32) {
            errors.rejectValue("name", "9001", "密码不能大于32位");
        }
    }

    private void validateStatus(Integer status, Errors errors) {
        if (status == null) {
            errors.rejectValue("name", "9001", "用户状态不能为空");
        }
    }
}
