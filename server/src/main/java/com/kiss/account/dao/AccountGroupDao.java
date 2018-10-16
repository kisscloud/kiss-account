package com.kiss.account.dao;

import com.kiss.account.entity.AccountGroup;
import com.kiss.account.exception.ResultException;

import java.util.List;

public interface AccountGroupDao {

    /**
     * 创建部门
     * @param accountGroup
     * @throws ResultException
     */
    void createAccountGroup(AccountGroup accountGroup) throws ResultException;

    /**
     * 根据部门的名字获取部门
     * @param name
     * @return
     */
    AccountGroup getAccountGroupByName(String name);

    /**
     * 查询部门信息
     * @return
     */
    List<AccountGroup> getGroups();

    /**
     * 根据部门id查询部门信息
     * @param id
     * @return
     */
    AccountGroup getGroupById (int id);

    /**
     * 更新部门信息
     * @param accountGroup
     * @return
     */
    Integer putAccountGroup(AccountGroup accountGroup);
}
