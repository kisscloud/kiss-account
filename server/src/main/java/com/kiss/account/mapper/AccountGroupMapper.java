package com.kiss.account.mapper;

import com.kiss.account.entity.AccountGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountGroupMapper {

    /**
     * 创建部门
     * @param accountGroup
     * @return
     */
    Integer createAccountGroup(AccountGroup accountGroup);

    /**
     * 根据部门名称查询部门信息
     * @param name
     * @return
     */
    AccountGroup getAccountGroupByName(String name);

    /**
     * 更新部门信息
     * @param accountGroup
     * @return
     */
    Integer updateAccountGroup(AccountGroup accountGroup);

    /**
     * 查询所有部门
     * @return
     */
    List<AccountGroup> getGroups();

    /**
     * 根据部门id查询部门信息
     * @param id
     * @return
     */
    AccountGroup getGroupById(int id);
}
