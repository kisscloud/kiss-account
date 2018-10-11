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

    public void allocateRoleToAccount(AccountRoles accountRoles) {
        accountMapper.allocateRoleToAccount(accountRoles);
    }

    public void allocateRolesToAccount(List<AccountRoles> accountRoles) {
        accountMapper.allocateRolesToAccount(accountRoles);
    }

    public List<Account> getAccounts() {
        return accountMapper.getAccounts();
    }

    /**z
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    public Account getAccountByUsername(String username) {
        Account account = accountMapper.getAccountByUsername(username);
        return account;
    }

    public Account getAccountById(Integer id) {
        Account account = accountMapper.getAccountById(id);
        return account;
    }
}
