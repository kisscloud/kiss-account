package com.kiss.account.dao;

import com.kiss.account.entity.AccountGroup;
import com.kiss.account.exception.ResultException;

import java.util.List;

public interface AccountGroupDao {

    /**
     * 创建部门
     *
     * @param accountGroup AccountGroup
     * @throws ResultException ResultException
     */
    void createAccountGroup(AccountGroup accountGroup) throws ResultException;

    /**
     * 根据部门的名字获取部门
     *
     * @param name String
     * @return AccountGroup
     */
    AccountGroup getAccountGroupByName(String name);

    /**
     * 查询部门信息
     *
     * @return List<AccountGroup>
     */
    List<AccountGroup> getGroups();

    /**
     * 根据部门id查询部门信息
     *
     * @param id int
     * @return AccountGroup
     */
    AccountGroup getGroupById(int id);

    /**
     * 更新部门信息
     *
     * @param accountGroup AccountGroup
     * @return Integer
     */
    Integer updateAccountGroup(AccountGroup accountGroup);
}
