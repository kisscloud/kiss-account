package com.kiss.account.dao;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;

import java.util.List;

public interface PermissionDao {

    /**
     * 创建权限
     *
     * @param permission Permission
     * @return Permission
     */
    Permission createPermission(Permission permission);

    /**
     * 创建权限模块
     *
     * @param permissionModule PermissionModule
     * @return PermissionModule
     */
    PermissionModule createPermissionModule(PermissionModule permissionModule);

    /**
     * 查询所有权限
     *
     * @return List<PermissionOutput>
     */
    List<PermissionOutput> getPermissions();

    /**
     * 查询可以绑定的权限列表
     *
     * @return List<BindPermissionOutput>
     */
    List<BindPermissionOutput> getBindPermissions();

    /**
     * 根据ID查询权限模块
     *
     * @param id Integer
     * @return PermissionModule
     */
    PermissionModule getPermissionModuleById(Integer id);

    /**
     * 查询权限模块列表
     *
     * @return List<PermissionModule>
     */
    List<PermissionModule> getPermissionModules();

    /**
     * 查询待绑定的权限模块列表
     *
     * @return List<PermissionModule>
     */
    List<PermissionModule> getBindPermissionModules();

    /**
     * 查询权限模块所绑定的权限数
     *
     * @param id Integer
     * @return Integer
     */
    Integer getPermissionModulePermissionsCount(Integer id);

    /**
     * 更新权限模块所绑定的权限数
     *
     * @param id               Integer  +1/-1 权限数加一或减一
     * @param permissionsCount Integer
     */
    void updatePermissionModulePermissionsCount(Integer id, Integer permissionsCount);

    /**
     * 根据权限名称或者权限码查询权限
     *
     * @param permission Permission
     * @return Permission
     */
    Permission getPermissionByNameOrCode(Permission permission);

    /**
     * 更新权限
     *
     * @param permissionOutput PermissionOutput
     * @return Integer
     */
    Integer updatePermission(PermissionOutput permissionOutput);

    /**
     * 根系权限模块
     *
     * @param permissionModuleOutput PermissionModuleOutput
     * @return Integer
     */
    Integer updatePermissionModule(PermissionModuleOutput permissionModuleOutput);

    /**
     * 删除权限
     *
     * @param id Integer
     * @return Integer
     */
    Integer deletePermission(Integer id);

    /**
     * 删除权限模块
     *
     * @param id Integer
     * @return Integer
     */
    Integer deletePermissionModule(Integer id);

    /**
     * 根据模块名查询模块信息
     *
     * @param name String
     * @return PermissionModule
     */
    PermissionModule getPermissionModuleByName(String name);

    /**
     * 根据模块id查询权限信息
     *
     * @param id Integer
     * @return List<Permission>
     */
    List<Permission> getPermissionByModuleId(Integer id);

    /**
     * 根据权限id查询权限信息
     *
     * @param id Integer
     * @return Permission
     */
    PermissionOutput getPermissionById(Integer id);

    /**
     * 根据权限id查询权限信息
     *
     * @param code String
     * @return Permission
     */
    Permission getPermissionByCode(String code);

    /**
     * 根据权限名称查询权限信息
     *
     * @param name String
     * @return Permission
     */
    Permission getPermissionByName(String name);
}
