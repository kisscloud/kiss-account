package com.kiss.account.mapper;

import com.kiss.account.entity.AccountGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountGroupMapper {

    /**
     * 创建部门
     *
     * @param accountGroup AccountGroup
     * @return Integer
     */
    Integer createAccountGroup(AccountGroup accountGroup);

    /**
     * 根据部门名称查询部门信息
     *
     * @param name String
     * @return AccountGroup
     */
    AccountGroup getAccountGroupByName(String name);

    /**
     * 根据部门名称查询部门信息
     *
     * @param id Integer
     * @return AccountGroup
     */
    AccountGroup getAccountGroupById(Integer id);

    /**
     * 更新部门信息
     *
     * @param accountGroup AccountGroup
     * @return Integer
     */
    Integer updateAccountGroup(AccountGroup accountGroup);

    /**
     * 查询所有部门
     *
     * @return List<AccountGroup>
     */
    List<AccountGroup> getAccountGroups();

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
