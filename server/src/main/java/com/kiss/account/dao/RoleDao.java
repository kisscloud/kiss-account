package com.kiss.account.dao;

import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermission;
import com.kiss.account.output.RoleOutput;
import com.kiss.account.output.RolePermissionOutput;

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
     * 根据角色id查询所有权限id
     *
     * @param id Integer
     * @return List<Integer>
     */
    List<Integer> getRolesPermissionIds(Integer id);

    /**
     * 根据角色id查询所有账号id
     *
     * @param id Integer
     * @return List<Integer>
     */
    List<Integer> getRolesAccountIds(Integer id);

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
     * @param roleOutput RoleOutput
     * @return Integer
     */
    Integer putRole(RoleOutput roleOutput);

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return Integer
     */
    Integer deleteRole(Integer id);

    /**
     * 删除角色对应的所有权限
     *
     * @param id 角色id
     * @return Integer
     */
    Integer deleteRolePermissions(Integer id);

    /**
     * 删除角色对应的用户
     *
     * @param id 角色id
     * @return
     */
    Integer deleteRoleAccounts(Integer id);

    /**
     * 绑定用户权限
     * @param rolePermission
     * @return
     */
    Integer bindRoleDataPermissions(RolePermissionOutput rolePermission);

    /**
     * 根据权限id和角色id查询角色权限
     * @param rolePermission 权限id
     * @return
     */
    RolePermission getRolePermission (RolePermission rolePermission);

    /**
     * 更新角色的权限
     * @param rolePermissions
     * @return
     */
    Integer updateRolePermissions (RolePermission rolePermissions);

    Integer deletePartRolePermissions (List<RolePermission> rolePermissions);
}
