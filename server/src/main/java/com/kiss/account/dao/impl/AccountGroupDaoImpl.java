package com.kiss.account.dao.impl;

import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountGroupMapper;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountGroupDaoImpl implements AccountGroupDao {

    @Autowired
    private AccountGroupMapper accountGroupMapper;

    public void createAccountGroup(AccountGroup accountGroup) {

        AccountGroup parentAccountGroup;

        if (accountGroup.getParentId() != 0) {
            parentAccountGroup = getGroupById(accountGroup.getParentId());
            if (parentAccountGroup != null) {
                accountGroup.setLevel(String.format("%s,%d,", parentAccountGroup.getLevel(), parentAccountGroup.getId()));
            }
        }

        accountGroupMapper.createAccountGroup(accountGroup);
    }

    public AccountGroup getAccountGroupByName(String name) {

        return accountGroupMapper.getAccountGroupByName(name);
    }

    @Override
    public AccountGroup getAccountGroupById(Integer id) {
        return accountGroupMapper.getAccountGroupById(id);
    }

    public List<AccountGroup> getGroups() {
        List<AccountGroup> groups = accountGroupMapper.getGroups();
        return groups;
    }

    public AccountGroup getGroupById(int id) {
        return accountGroupMapper.getAccountGroupById(id);
    }

    public Integer updateAccountGroup(AccountGroup accountGroup) {


        AccountGroup parentAccountGroup;

        if (accountGroup.getParentId() != 0) {
            parentAccountGroup = getGroupById(accountGroup.getParentId());
            if (parentAccountGroup != null) {
                accountGroup.setLevel(String.format("%s%d,", parentAccountGroup.getLevel(), parentAccountGroup.getId()));
            }
        } else {
            accountGroup.setLevel("0,");
        }

        return accountGroupMapper.updateAccountGroup(accountGroup);
    }

    @Override
    public void deleteGroup(Integer id) {
        accountGroupMapper.deleteGroup(id);
    }

    @Override
    public List<AccountGroup> getAccountGroupChildren(Integer id) {
        return accountGroupMapper.getAccountGroupChildren(id);
    }
}
