package com.kiss.account.mapper;

import com.kiss.account.entity.PermissionModule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionModuleMapper {
    /**
     * 创建权限模块Mapper
     *
     * @param permissionModule PermissionModule
     */
    void createPermissionModule(PermissionModule permissionModule);
}
