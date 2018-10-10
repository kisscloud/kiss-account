package com.kiss.account.dao;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountRoles;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDao {
    @Autowired
    private AccountMapper accountMapper;

    public void createAccount(Account account) throws ResultException {
        accountMapper.createAccount(account);
    }

    public void allocateRoleToAcount(AccountRoles accountRoles) {
        accountMapper.allocateRoleToAcount(accountRoles);
    }

    public void allocateRolesToAcount(List<AccountRoles> accountRoles) {
        accountMapper.allocateRolesToAcount(accountRoles);
    }

    public List<Account> getAccounts() {
        return accountMapper.getAccounts();
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    public Account getAccountByUsername(String username) {
        Account account = accountMapper.getAccountByUsername(username);
        return account;
    }
}
