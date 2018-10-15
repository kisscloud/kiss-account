package com.kiss.account.mapper;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionModuleMapper {
    /**
     * 创建权限模块Mapper
     *
     * @param permissionModule PermissionModule
     */
    void createPermissionsModules(PermissionModule permissionModule);

    /**
     * 根据ID查询权限模块Mapper
     *
     * @param id Integer
     * @return PermissionModule
     */
    PermissionModule getPermissionModuleById(Integer id);

    /**
     * 获取权限模块列表Mapper
     *
     * @return List<PermissionModule>
     */
    List<PermissionModule> getPermissionsModules();

    /**
     * 获取待绑定的权限模块列表Mapper
     *
     * @return List<PermissionModule>
     */
    List<PermissionModule> getBindPermissionsModules();

    /**
     * 获取权限模块所绑定的权限数Mapper
     *
     * @param id Integer
     * @return Integer
     */
    Integer getPermissionModulePermissionsCount(Integer id);

    /**
     * 更新权限模块所绑定的权限数Mapper
     *
     * @param permissionModule PermissionModule
     */
    void updatePermissionModulePermissionsCount(PermissionModule permissionModule);
}
