package com.kiss.account.mapper;

import com.kiss.account.entity.PermissionModule;
import com.kiss.account.output.PermissionModuleOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionModuleMapper {
    /**
     * 创建权限模块
     *
     * @param permissionModule PermissionModule
     */
    void createPermissionsModules(PermissionModule permissionModule);

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
     * @param permissionModule PermissionModule
     */
    void updatePermissionModulePermissionsCount(PermissionModule permissionModule);

    /**
     * 根据模块名称查询模块信息
     * @param name
     * @return
     */
    PermissionModule getPermissionModuleByName (String name);

    /**
     * 删除权限模块
     * @param id
     * @return
     */
    Integer deletePermissionModule (Integer id);

    /**
     * 更新权限模块
     * @param permissionModuleOutput
     * @return
     */
    Integer updatePermissionModule (PermissionModuleOutput permissionModuleOutput);

    /**
     * 根据模块id查询其子模块
     * @param id
     * @return
     */
    List<PermissionModule> getPermissionModuleChildren(Integer id);
}
