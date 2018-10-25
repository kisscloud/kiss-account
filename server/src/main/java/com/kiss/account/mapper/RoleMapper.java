package com.kiss.account.mapper;

import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 创建角色
     *
     * @param role
     */
    void createRole(Role role);

    /**
     * 给角色分配权限
     *
     * @param rolePermissions
     */
    void bindPermissionsToRole(List<RolePermission> rolePermissions);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> getRoles();

    /**
     * 查询角色下所有的权限
     *
     * @param roleId 角色id
     * @return
     */
    List<RolePermission> getRolePermissionsByRoleId(Integer roleId);

    /**
     * 查询角色下所有的账户id
     *
     * @param roleId 角色id
     * @return
     */
    List<Integer> getRolesAccountIdsByRoleId(Integer roleId);

    /**
     * 根据角色ID查询角色信息
     *
     * @param id
     * @return
     */
    Role getRoleById(Integer id);

    /**
     * 根据角色名查询角色信息
     *
     * @param name
     * @return
     */
    Role getRoleByName(String name);

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    Integer updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    Integer deleteRoleById(Integer id);

    /**
     * 删除角色下所有的权限
     *
     * @param roleId
     * @return
     */
    Integer deleteRolePermissionsByRoleId(Integer roleId);

    /**
     * 删除角色下所有用户
     *
     * @param roleId 角色id
     * @return
     */
    Integer deleteRoleAccountsByRoleId(Integer roleId);

    /**
     * 查询有效角色的数量
     * @return
     */
    Integer getValidRoleCount();
}
