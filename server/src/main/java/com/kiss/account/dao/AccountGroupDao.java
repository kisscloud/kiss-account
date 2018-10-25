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
     * 根据部门的名字查询部门
     *
     * @param name String
     * @return AccountGroup
     */
    AccountGroup getAccountGroupByName(String name);

    /**
     * 根据部门ID查询部门
     *
     * @param id Integer
     * @return AccountGroup
     */
    AccountGroup getAccountGroupById(Integer id);

    /**
     * 查询部门信息
     *
     * @return List<AccountGroup>
     */
    List<AccountGroup> getAccountGroups();

    /**
     * 更新部门信息
     *
     * @param accountGroup AccountGroup
     * @return Integer
     */
    Integer updateAccountGroup(AccountGroup accountGroup);

    /**
     * 删除部门
     *
     * @param id Integer
     */
    void deleteAccountGroupById(Integer id);

    /**
     * 查询部门下所有的子部门
     * @param parentId
     * @return
     */
    List<AccountGroup> getAccountGroupChildrenByParentId(Integer parentId);

    /**
     * 查询部门的数量
     * @return
     */
    Integer getAccountGroupCount();
}
