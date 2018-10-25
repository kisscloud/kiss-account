package com.kiss.account.dao.impl;

import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountRole;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountMapper;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.AccountRoleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void createAccount(Account account) throws ResultException {

        accountMapper.createAccount(account);
    }

    @Override
    public void bindRolesToAccount(List<AccountRole> accountRoles) {

        accountMapper.bindRolesToAccount(accountRoles);
    }

    @Override
    public List<Account> getAccounts(int start, int size) {

        return accountMapper.getAccounts(start, size);
    }

    @Override
    public List<Account> getAccountsByGroupId(Integer groupId) {
        return accountMapper.getAccountsByGroupId(groupId);
    }

    @Override
    public Account getAccountByUsername(String username) {

        return accountMapper.getAccountByUsername(username);
    }

    @Override
    public Account getAccountByEmail(String email) {

        return accountMapper.getAccountByEmail(email);
    }

    @Override
    public Account getAccountByMobile(String mobile) {

        return accountMapper.getAccountByMobile(mobile);
    }

    @Override
    public AccountOutput getAccountOutputById(Integer id) {

        return accountMapper.getAccountOutputById(id);
    }

    @Override
    public Integer getAccountsCount() {

        return accountMapper.getAccountsCount();
    }

    @Override
    public Integer updateAccount(Account account) {

        return accountMapper.updateAccount(account);
    }

    @Override
    public Integer updateAccountPassword(Account account) {

        return accountMapper.updateAccountPassword(account);
    }

    @Override
    public Integer updateAccountStatus(Account account) {
        return accountMapper.updateAccountStatus(account);

    }

    @Override
    public Integer deleteAccountRoles(Integer id) {

        return accountMapper.deleteAccountRoles(id);
    }

    @Override
    public List<String> getAccountPermissions(Integer id) {

        return accountMapper.getAccountPermissions(id);
    }

    @Override
    public Account getAccountById(Integer id) {

        return accountMapper.getAccountById(id);
    }

    @Override
    public Account getAccountByName(String name) {

        return accountMapper.getAccountByName(name);
    }

    @Override
    public List<String> getAccountPermissionDataScope(Integer id, String code) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("code", code);
        return accountMapper.getAccountPermissionDataScope(params);
    }

    @Override
    public Integer getValidAccountsCount() {
        return accountMapper.getValidAccountsCount();
    }
}
