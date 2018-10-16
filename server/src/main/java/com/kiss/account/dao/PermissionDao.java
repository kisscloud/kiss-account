package com.kiss.account.dao;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.output.AllocatePermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;

import java.util.List;

public interface PermissionDao {

    /**
     * 创建权限
     * @param permission
     * @return
     */
    Permission createPermission(Permission permission);

    /**
     * 创建权限模块
     * @param permissionModule
     * @return
     */
    PermissionModule createPermissionModule(PermissionModule permissionModule);

    /**
     * 查询所有权限
     * @return
     */
    List<PermissionOutput> getPermissions();

    /**
     * 查询可以绑定的权限列表
     * @return
     */
    List<AllocatePermissionOutput> getBindPermissions();

    /**
     * 根据ID查询权限模块
     * @param id
     * @return
     */
    PermissionModule getPermissionModuleById(Integer id);

    /**
     * 查询权限模块列表
     * @return
     */
    List<PermissionModule> getPermissionsModules();

    /**
     * 查询待绑定的权限模块列表
     * @return
     */
    List<PermissionModule> getBindPermissionsModules();

    /**
     * 查询权限模块所绑定的权限数
     * @param id
     * @return
     */
    Integer getPermissionModulePermissionsCount(Integer id);

    /**
     * 更新权限模块所绑定的权限数
     * @param id                +1/-1 权限数加一或减一
     * @param permissionsCount
     */
    void putPermissionModulePermissionsCount(Integer id, Integer permissionsCount);

    /**
     * 根据权限名称或者权限码查询权限
     * @param permission
     * @return
     */
    Permission getPermissionByNameOrCode (Permission permission);

    /**
     * 更新权限
     * @param permissionOutput
     * @return
     */
    Integer putPermission (PermissionOutput permissionOutput);

    /**
     * 根系权限模块
     * @param permissionModuleOutput
     * @return
     */
    Integer putPermissionModule (PermissionModuleOutput permissionModuleOutput);

    /**
     * 删除权限
     * @param id
     * @return
     */
    Integer deletePermission (Integer id);

    /**
     * 删除权限模块
     * @param id
     * @return
     */
    Integer deletePermissionModule (Integer id);

    /**
     * 根据模块名查询模块信息
     * @param name
     * @return
     */
    PermissionModule getPermissionModuleByName (String name);

    /**
     * 根据模块id查询权限信息
     * @param id
     * @return
     */
    List<Permission> getPermissionByModuleId (Integer id);
}
