package com.kiss.account.dao;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.mapper.PermissionMapper;
import com.kiss.account.mapper.PermissionModuleMapper;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


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

        permissionModuleMapper.createPermissionsModules(permissionModule);

        return permissionModule;
    }


    /**
     * 获取所有权限DAO
     *
     * @return List<Permission>
     */
    public List<PermissionOutput> getPermissions() {
        return permissionMapper.getPermissions();
    }


    /**
     * 获取权限模块列表DAO
     *
     * @return List<PermissionModule>
     */
    public List<PermissionModule> getPermissionsModules() {
        return permissionModuleMapper.getPermissionsModules();
    }

    /**
     * 获取可以绑定的权限列表DAO
     *
     * @return List<BindPermissionOutput>
     */
    public List<BindPermissionOutput> getBindPermissions() {
        return permissionMapper.getBindPermissions(1);
    }
}
