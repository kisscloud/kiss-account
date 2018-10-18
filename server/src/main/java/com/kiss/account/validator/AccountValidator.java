package com.kiss.account.validator;

import com.kiss.account.utils.ApplicationUtil;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import com.kiss.account.input.AccountInfoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AccountValidator implements Validator {

    private AccountDao accountDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AccountInfoInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDao accountDao = (AccountDao) ApplicationUtil.getBean(AccountDao.class);

        Account account = accountDao.getAccountById(18);

        AccountInfoInput accountInfoInput = (AccountInfoInput) target;
        if (!accountInfoInput.getName().equals("abc")) {
            errors.rejectValue("name", null, "用户名不对");
        }
    }

    @Autowired
    public void setAccountDao(final AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
