package com.kiss.account.dao;

import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermissions;
import com.kiss.account.output.RoleOutput;

import java.util.List;

public interface RoleDao {

    /**
     * 创建角色
     * @param role
     */
    void createRole(Role role);

    /**
     * 给角色分配权限
     * @param rolePermissions
     */
    void allocatePermissionsToRole(List<RolePermissions> rolePermissions);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> getRoles();

    /**
     * 根据角色id查询所有权限id
     * @param id
     * @return
     */
    List<Integer> getRolesPermissionIds(Integer id);

    /**
     * 根据角色id查询所有账号id
     * @param id
     * @return
     */
    List<Integer> getRolesAccountIds(Integer id);

    /**
     * 根据角色名查询角色信息
     * @param name
     * @return
     */
    Role getRoleByName (String name);

    /**
     * 更新角色
     * @param roleOutput
     * @return
     */
    Integer putRole (RoleOutput roleOutput);

    /**
     * 删除角色
     * @param id 角色id
     * @return
     */
    Integer deleteRole (Integer id);

    /**
     * 删除角色对应的所有权限
     * @param id 角色id
     * @return
     */
    Integer deleteRolePermissions (Integer id);
}
