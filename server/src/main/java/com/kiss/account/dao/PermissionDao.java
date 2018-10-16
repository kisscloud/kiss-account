package com.kiss.account.dao;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.mapper.PermissionMapper;
import com.kiss.account.mapper.PermissionModuleMapper;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
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

        if (permissionModule.getLevel() == null) {
            permissionModule.setLevel("");
        } else {
            permissionModule.setLevel(StringUtils.removeStart(permissionModule.getLevel(), ","));
        }
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
     * 获取可以绑定的权限列表DAO
     *
     * @return List<BindPermissionOutput>
     */
    public List<BindPermissionOutput> getBindPermissions() {
        return permissionMapper.getBindPermissions(1);
    }

    /**
     * 根据ID查询权限模块DAO
     *
     * @param id Integer
     * @return PermissionModule
     */
    public PermissionModule getPermissionModuleById(Integer id) {
        return permissionModuleMapper.getPermissionModuleById(id);
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
     * 获取待绑定的权限模块列表DAO
     *
     * @return List<PermissionModule>
     */
    public List<PermissionModule> getBindPermissionsModules() {
        return permissionModuleMapper.getBindPermissionsModules();
    }

    /**
     * 获取权限模块所绑定的权限数DAO
     *
     * @param id Integer
     * @return Integer
     */
    public Integer getPermissionModulePermissionsCount(Integer id) {
        return permissionModuleMapper.getPermissionModulePermissionsCount(id);
    }

    /**
     * 更新权限模块所绑定的权限数DAO
     *
     * @param id               Integer
     * @param permissionsCount Integer  +1/-1 权限数加一或减一
     */
    public void updatePermissionModulePermissionsCount(Integer id, Integer permissionsCount) {
        Integer oldCount = getPermissionModulePermissionsCount(id);
        PermissionModule permissionModule = new PermissionModule();
        permissionModule.setId(id);
        permissionModule.setPermissions(oldCount + permissionsCount);
        permissionModuleMapper.updatePermissionModulePermissionsCount(permissionModule);
    }

    /**
     * 根据权限名称或者权限码获取权限
     * @param permission
     * @return
     */
    public Permission getPermissionByNameOrCode (Permission permission) {
        return permissionMapper.getPermissionByNameOrCode(permission);
    }

    public Integer putPermission (PermissionOutput permissionOutput) {
        return permissionMapper.putPermission(permissionOutput);
    }

    public Integer putPermissionModule (PermissionModuleOutput permissionModuleOutput) {
        return permissionModuleMapper.putPermissionModule(permissionModuleOutput);
    }

    public Integer deletePermission (Integer id) {
        return permissionMapper.deletePermission(id);
    }

    public Integer deletePermissionModule (Integer id) {
        return permissionModuleMapper.deletePermissionModule(id);
    }

    public PermissionModule getPermissionModuleByName (String name) {
        return permissionModuleMapper.getPermissionModuleByName(name);
    }

    public List<Permission> getPermissionByModuleId (Integer id) {
        return permissionMapper.getPermissionByModuleId(id);
    }

}

