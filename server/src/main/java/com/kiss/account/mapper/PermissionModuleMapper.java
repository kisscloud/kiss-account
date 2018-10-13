package com.kiss.account.mapper;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
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
     * 获取权限模块列表Mapper
     *
     * @return List<PermissionModule>
     */
    List<PermissionModule> getPermissionsModules();
}
