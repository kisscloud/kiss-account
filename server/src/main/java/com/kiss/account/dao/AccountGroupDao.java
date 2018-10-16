package com.kiss.account.dao;

import com.kiss.account.entity.AccountGroup;
import com.kiss.account.exception.ResultException;
import com.kiss.account.mapper.AccountGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountGroupDao {

    @Autowired
    private AccountGroupMapper accountGroupMapper;

    public void createAccountGroup(AccountGroup accountGroup) throws ResultException {
        accountGroupMapper.createAccountGroup(accountGroup);
    }

    /**
     * 根据部门的名字查找部门
     * @param name
     * @return
     */
    public AccountGroup getAccountGroupByName(String name) {
        return accountGroupMapper.getAccountGroupByName(name);
    }

    public Integer putAccountGroup(AccountGroup accountGroup) {
        return accountGroupMapper.putAccountGroup(accountGroup);
    }
}
