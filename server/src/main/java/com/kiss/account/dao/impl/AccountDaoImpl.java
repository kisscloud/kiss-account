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

    public void createAccount(Account account) throws ResultException {
        accountMapper.createAccount(account);
    }

    public void allocateRolesToAccount(List<AccountRoleOutput> accountRoles) {
        accountMapper.allocateRolesToAccount(accountRoles);
    }

    public List<AccountOutput> getAccounts(int start, int size) {
        return accountMapper.getAccounts(start,size);
    }

    public Account getAccountByUsername(String username) {
        Account account = accountMapper.getAccountByUsername(username);
        return account;
    }

    public AccountOutput getAccountById(Integer id) {
        AccountOutput account = accountMapper.getAccountById(id);
        return account;
    }

    public Integer getAccountsCount() {
        return accountMapper.getAccountsCount();
    }

    public Account getAccountByUniqueIdentification(String name,String username,String email,String mobile) {
        return accountMapper.getAccountByUniqueIdentification(name,username,email,mobile);
    }

    public Integer putAccount(AccountOutput account) {
        return accountMapper.putAccount(account);
    }

    public Integer putAccountPassword(Account account) {
        return accountMapper.putAccountPassword(account);
    }

    public Integer putAccountStatus(AccountOutput accountOutput) {
        return accountMapper.putAccountStatus(accountOutput);
    }

    public Integer deleteAccountRoles (Integer id) {
        return accountMapper.deleteAccountRoles(id);
    }
}
