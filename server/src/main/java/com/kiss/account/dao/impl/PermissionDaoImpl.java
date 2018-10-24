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

    @Override
    public Permission createPermission(Permission permission) {

        permissionMapper.createPermission(permission);

        return permission;
    }

    @Override
    public PermissionModule createPermissionModule(PermissionModule permissionModule) {

        if (permissionModule.getParentId() != 0) {
            PermissionModule parentPermissionModule = getPermissionModuleById(permissionModule.getParentId());
            permissionModule.setLevel(String.format("%s%d,", parentPermissionModule.getLevel(), parentPermissionModule.getId()));
        }

        permissionModuleMapper.createPermissionsModules(permissionModule);

        return permissionModule;
    }

    @Override
    public List<PermissionOutput> getPermissions() {

        return permissionMapper.getPermissions();
    }

    @Override
    public List<BindPermissionOutput> getBindPermissions() {

        return permissionMapper.getBindPermissions(1);
    }

    @Override
    public PermissionModule getPermissionModuleById(Integer id) {

        return permissionModuleMapper.getPermissionModuleById(id);
    }

    @Override
    public List<PermissionModule> getPermissionModules() {

        return permissionModuleMapper.getPermissionModules();
    }

    @Override
    public List<PermissionModule> getBindPermissionModules() {

        return permissionModuleMapper.getBindPermissionModules();
    }

    @Override
    public void updatePermissionModulePermissionsCount(Integer id, Integer permissionsCount) {

        Integer oldCount = permissionModuleMapper.getPermissionModulePermissionsCountById(id);
        PermissionModule permissionModule = new PermissionModule();
        permissionModule.setId(id);
        permissionModule.setPermissions(oldCount + permissionsCount);
        permissionModuleMapper.updatePermissionModulePermissionsCount(permissionModule);
    }

    @Override
    public Permission getPermissionByNameOrCode(Permission permission) {

        return permissionMapper.getPermissionByNameOrCode(permission);
    }

    @Override
    public Integer updatePermission(Permission permission) {

        return permissionMapper.updatePermission(permission);
    }

    @Override
    public Integer updatePermissionModule(PermissionModuleOutput permissionModuleOutput) {

        if (permissionModuleOutput.getParentId() != 0) {
            PermissionModule parentPermissionModule = getPermissionModuleById(permissionModuleOutput.getParentId());
            permissionModuleOutput.setLevel(String.format("%s%d,", parentPermissionModule.getLevel(), parentPermissionModule.getId()));
        } else {
            permissionModuleOutput.setLevel("0,");
        }

        return permissionModuleMapper.updatePermissionModule(permissionModuleOutput);
    }

    @Override
    public Integer deletePermissionById(Integer id) {

        return permissionMapper.deletePermissionById(id);
    }

    @Override
    public Integer deletePermissionModuleById(Integer id) {

        return permissionModuleMapper.deletePermissionModuleById(id);
    }

    @Override
    public PermissionModule getPermissionModuleByName(String name) {

        return permissionModuleMapper.getPermissionModuleByName(name);
    }

    @Override
    public List<Permission> getPermissionByModuleId(Integer moduleId) {

        return permissionMapper.getPermissionByModuleId(moduleId);
    }

    @Override
    public Permission getPermissionById(Integer id) {

        return permissionMapper.getPermissionById(id);
    }

    @Override
    public Permission getPermissionByCode(String code) {

        return permissionMapper.getPermissionByCode(code);
    }

    @Override
    public List<PermissionModule> getPermissionModuleChildrenByParentId(Integer parentId) {

        return permissionModuleMapper.getPermissionModuleChildrenByParentId(parentId);
    }

    @Override
    public Integer getValidPermissionCount() {

        return permissionMapper.getValidPermissionCount();
    }

    ;

}

