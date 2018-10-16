package com.kiss.account.dao.impl;

import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountMapper;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.AccountRoleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void createAccount(Account account) throws ResultException {

        accountMapper.createAccount(account);
    }

    @Override
    public void bindRolesToAccount(List<AccountRoleOutput> accountRoles) {

        accountMapper.bindRolesToAccount(accountRoles);
    }

    @Override
    public List<AccountOutput> getAccounts(int start, int size) {

        return accountMapper.getAccounts(start,size);
    }

    @Override
    public Account getAccountByUsername(String username) {

        Account account = accountMapper.getAccountByUsername(username);
        return account;
    }

    @Override
    public AccountOutput getAccountById(Integer id) {

        AccountOutput account = accountMapper.getAccountById(id);
        return account;
    }

    @Override
    public Integer getAccountsCount() {

        return accountMapper.getAccountsCount();
    }

    @Override
    public Account getAccountByUniqueIdentification(String name,String username,String email,String mobile) {

        return accountMapper.getAccountByUniqueIdentification(name,username,email,mobile);
    }

    @Override
    public Integer updateAccount(AccountOutput account) {

        return accountMapper.updateAccount(account);
    }

    @Override
    public Integer updateAccountPassword(Account account) {

        return accountMapper.updateAccountPassword(account);
    }

    @Override
    public Integer updateAccountStatus(AccountOutput accountOutput) {
        return accountMapper.updateAccountStatus(accountOutput);
    }

    @Override
    public Integer deleteAccountRoles (Integer id) {

        return accountMapper.deleteAccountRoles(id);
    }

    @Override
    public List<String> getAccountPermissions(Integer id) {

        return accountMapper.getAccountPermissions(id);
    }
}
