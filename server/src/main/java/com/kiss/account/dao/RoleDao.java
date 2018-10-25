package com.kiss.account.dao;

import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermission;

import java.util.List;

public interface RoleDao {

    /**
     * 创建角色
     *
     * @param role Role
     */
    void createRole(Role role);

    /**
     * 给角色分配权限
     *
     * @param rolePermissions (List<RolePermissions>
     */
    void bindPermissionsToRole(List<RolePermission> rolePermissions);

    /**
     * 查询所有角色
     *
     * @return List<Role>
     */
    List<Role> getRoles();

    /**
     * 根据角色id查询所有权限
     *
     * @param roleId Integer
     * @return List<RolePermission>
     */
    List<RolePermission> getRolePermissionsByRoleId(Integer roleId);

    /**
     * 根据角色id查询所有账号id
     *
     * @param roleId Integer
     * @return List<Integer>
     */
    List<Integer> getRolesAccountIdsByRoleId(Integer roleId);

    /**
     * 根据角色ID查询角色信息
     *
     * @param id Integer
     * @return Role
     */
    Role getRoleById(Integer id);

    /**
     * 根据角色名查询角色信息
     *
     * @param name String
     * @return Role
     */
    Role getRoleByName(String name);

    /**
     * 更新角色
     *
     * @param role
     * @return Integer
     */
    Integer updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return Integer
     */
    Integer deleteRoleById(Integer id);

    /**
     * 删除角色对应的所有权限
     *
     * @param roleId 角色id
     * @return Integer
     */
    Integer deleteRolePermissionsByRoleId(Integer roleId);

    /**
     * 删除角色对应的用户
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
