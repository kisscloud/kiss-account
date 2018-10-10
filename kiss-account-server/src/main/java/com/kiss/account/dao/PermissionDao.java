package com.kiss.account.dao;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.mapper.PermissionMapper;
import com.kiss.account.mapper.PermissionModuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PermissionDao {

    @Autowired
    private PermissionMapper permissionMapper;


    @Autowired
    private PermissionModuleMapper permissionModuleMapper;

    /**
     * 创建权限DAO
     *
     * @param permission Permission
     * @return Permission
     */
    public Permission createPermission(Permission permission) {

        permissionMapper.createPermission(permission);

        return permission;
    }


    /**
     * 创建权限模块DAO
     *
     * @param permissionModule PermissionModule
     * @return PermissionModule
     */
    public PermissionModule createPermissionModule(PermissionModule permissionModule) {

        permissionModuleMapper.createPermissionModule(permissionModule);

        return permissionModule;
    }

}
