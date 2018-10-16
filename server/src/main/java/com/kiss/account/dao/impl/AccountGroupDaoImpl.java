package com.kiss.account.dao.impl;

import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountGroupDaoImpl implements AccountGroupDao {

    @Autowired
    private AccountGroupMapper accountGroupMapper;

    public void createAccountGroup(AccountGroup accountGroup) throws ResultException {
        accountGroupMapper.createAccountGroup(accountGroup);
    }

    public AccountGroup getAccountGroupByName(String name) {
        return accountGroupMapper.getAccountGroupByName(name);
    }

    public List<AccountGroup> getGroups() {
        List<AccountGroup> groups = accountGroupMapper.getGroups();
        return groups;
    }

    public AccountGroup getGroupById (int id) {
        return accountGroupMapper.getGroupById(id);
    }

    public Integer putAccountGroup(AccountGroup accountGroup) {
        return accountGroupMapper.putAccountGroup(accountGroup);
    }
}
