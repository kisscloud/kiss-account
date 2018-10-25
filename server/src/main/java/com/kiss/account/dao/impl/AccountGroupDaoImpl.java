package com.kiss.account.dao.impl;

import com.kiss.account.dao.AccountGroupDao;
import com.kiss.account.entity.AccountGroup;
import com.kiss.account.mapper.AccountGroupMapper;
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
            parentAccountGroup = getAccountGroupById(accountGroup.getParentId());
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

    public List<AccountGroup> getAccountGroups() {

        List<AccountGroup> groups = accountGroupMapper.getAccountGroups();
        return groups;
    }

    public Integer updateAccountGroup(AccountGroup accountGroup) {

        AccountGroup parentAccountGroup;

        if (accountGroup.getParentId() != 0) {
            parentAccountGroup = getAccountGroupById(accountGroup.getParentId());
            if (parentAccountGroup != null) {
                accountGroup.setLevel(String.format("%s%d,", parentAccountGroup.getLevel(), parentAccountGroup.getId()));
            }
        } else {
            accountGroup.setLevel("0,");
        }

        return accountGroupMapper.updateAccountGroup(accountGroup);
    }

    @Override
    public void deleteAccountGroupById(Integer id) {

        accountGroupMapper.deleteAccountGroupById(id);
    }

    @Override
    public List<AccountGroup> getAccountGroupChildrenByParentId(Integer parentId) {

        return accountGroupMapper.getAccountGroupChildrenByParentId(parentId);
    }

    @Override
    public Integer getAccountGroupCount() {

        return accountGroupMapper.getAccountGroupCount();
    }
}
