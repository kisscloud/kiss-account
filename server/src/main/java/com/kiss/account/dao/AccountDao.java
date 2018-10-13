package com.kiss.account.dao;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.entity.AccountRoles;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountMapper;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.AccountRolesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    public void allocateRolesToAccount(List<AccountRolesOutput> accountRoles) {
        accountMapper.allocateRolesToAccount(accountRoles);
    }

    public List<AccountOutput> getAccounts(int start, int size) {
        return accountMapper.getAccounts(start,size);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     *
     * @return Account
     */
    public Account getAccountByUsername(String username) {
        Account account = accountMapper.getAccountByUsername(username);
        return account;
    }

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return AccountOutput
     */
    public AccountOutput getAccountById(Integer id) {
        AccountOutput account = accountMapper.getAccountById(id);
        return account;
    }

    /**
     * 获取所有部门信息
     * @return List<AccountGroup>
     */
    public List<AccountGroup> getGroups() {
        List<AccountGroup> groups = accountMapper.getGroups();
        return groups;
    }

    /**
     * 根据部门id获取部门信息
     * @param id 部门id
     * @return AccountGroup
     */
    public AccountGroup getGroup (int id) {
        return accountMapper.getGroup(id);
    }

    /**
     * 获取所有用户数量
     * @return Integer
     */
    public Integer getAccountsCount() {
        return accountMapper.getAccountsCount();
    }
}
