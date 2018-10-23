package com.kiss.account.dao.impl;

import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.mapper.PermissionMapper;
import com.kiss.account.mapper.PermissionModuleMapper;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private PermissionMapper permissionMapper;


    @Autowired
    private PermissionModuleMapper permissionModuleMapper;

    public Permission createPermission(Permission permission) {

        permissionMapper.createPermission(permission);

        return permission;
    }

    public PermissionModule createPermissionModule(PermissionModule permissionModule) {

        if (permissionModule.getParentId() != 0) {
            PermissionModule parentPermissionModule = getPermissionModuleById(permissionModule.getParentId());
            permissionModule.setLevel(String.format("%s%d,", parentPermissionModule.getLevel(), parentPermissionModule.getId()));
        }

        permissionModuleMapper.createPermissionsModules(permissionModule);

        return permissionModule;
    }

    public List<PermissionOutput> getPermissions() {
        return permissionMapper.getPermissions();
    }

    public List<BindPermissionOutput> getBindPermissions() {

        return permissionMapper.getBindPermissions(1);
    }

    public PermissionModule getPermissionModuleById(Integer id) {

        return permissionModuleMapper.getPermissionModuleById(id);
    }

    public List<PermissionModule> getPermissionModules() {

        return permissionModuleMapper.getPermissionModules();
    }

    public List<PermissionModule> getBindPermissionModules() {

        return permissionModuleMapper.getBindPermissionModules();
    }

    public Integer getPermissionModulePermissionsCount(Integer id) {

        return permissionModuleMapper.getPermissionModulePermissionsCount(id);
    }

    public void updatePermissionModulePermissionsCount(Integer id, Integer permissionsCount) {

        Integer oldCount = getPermissionModulePermissionsCount(id);
        PermissionModule permissionModule = new PermissionModule();
        permissionModule.setId(id);
        permissionModule.setPermissions(oldCount + permissionsCount);
        permissionModuleMapper.updatePermissionModulePermissionsCount(permissionModule);
    }

    public Permission getPermissionByNameOrCode(Permission permission) {

        return permissionMapper.getPermissionByNameOrCode(permission);
    }

    public Integer updatePermission(PermissionOutput permissionOutput) {

        return permissionMapper.updatePermission(permissionOutput);
    }

    public Integer updatePermissionModule(PermissionModuleOutput permissionModuleOutput) {

        if (permissionModuleOutput.getParentId() != 0) {
            PermissionModule parentPermissionModule = getPermissionModuleById(permissionModuleOutput.getParentId());
            permissionModuleOutput.setLevel(String.format("%s%d,", parentPermissionModule.getLevel(), parentPermissionModule.getId()));
        } else {
            permissionModuleOutput.setLevel("0,");
        }

        return permissionModuleMapper.updatePermissionModule(permissionModuleOutput);
    }

    public Integer deletePermission(Integer id) {
        return permissionMapper.deletePermission(id);
    }

    public Integer deletePermissionModule(Integer id) {
        return permissionModuleMapper.deletePermissionModule(id);
    }

    public PermissionModule getPermissionModuleByName(String name) {

        return permissionModuleMapper.getPermissionModuleByName(name);
    }

    public List<Permission> getPermissionByModuleId(Integer id) {

        return permissionMapper.getPermissionByModuleId(id);
    }

    @Override
    public PermissionOutput getPermissionById(Integer id) {

        return permissionMapper.getPermissionById(id);
    }

    @Override
    public Permission getPermissionByCode(String code) {

        return permissionMapper.getPermissionByCode(code);
    }

    @Override
    public Permission getPermissionByName(String name) {

        return permissionMapper.getPermissionByName(name);
    }

    public List<PermissionModule> getPermissionModuleChildren(Integer id) {

        return permissionModuleMapper.getPermissionModuleChildren(id);
    }

    ;

}

