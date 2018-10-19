package com.kiss.account.mapper;

import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermissions;
import com.kiss.account.output.RoleOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 创建角色
     * @param role
     */
    void createRole(Role role);

    /**
     * 给角色分配权限
     * @param rolePermissions
     */
    void bindPermissionsToRole(List<RolePermissions> rolePermissions);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> getRoles();

    /**
     * 查询角色下所有的权限id
     * @param id 角色id
     * @return
     */
    List<Integer> getRolesPermissionIds(Integer id);

    /**
     * 查询角色下所有的账户id
     * @param id 角色id
     * @return
     */
    List<Integer> getRolesAccountIds(Integer id);

    /**
     * 根据角色名查询角色信息
     * @param name
     * @return
     */
    Role getRoleByName(String name);

    /**
     * 更新角色信息
     * @param roleOutput
     * @return
     */
    Integer putRole(RoleOutput roleOutput);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Integer deleteRole(Integer id);

    /**
     * 删除角色下所有的权限
     * @param id
     * @return
     */
    Integer deleteRolePermissions(Integer id);

    /**
     * 删除角色下所有用户
     * @param id 角色id
     * @return
     */
    Integer deleteRoleAccounts(Integer id);
}
