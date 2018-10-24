package com.kiss.account.dao.impl;

import com.kiss.account.dao.RoleDao;
import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermission;
import com.kiss.account.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void createRole(Role role) {

        roleMapper.createRole(role);
    }

    @Override
    public void bindPermissionsToRole(List<RolePermission> rolePermissions) {

        roleMapper.bindPermissionsToRole(rolePermissions);
    }

    @Override
    public List<Role> getRoles() {

        return roleMapper.getRoles();
    }

    @Override
    public List<RolePermission> getRolePermissionsByRoleId(Integer roleId) {

        return roleMapper.getRolePermissionsByRoleId(roleId);
    }

    @Override
    public List<Integer> getRolesAccountIdsByRoleId(Integer roleId) {

        return roleMapper.getRolesAccountIdsByRoleId(roleId);
    }

    @Override
    public Role getRoleById(Integer id) {

        return roleMapper.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {

        return roleMapper.getRoleByName(name);
    }

    @Override
    public Integer updateRole(Role role) {

        return roleMapper.updateRole(role);
    }

    @Override
    public Integer deleteRoleById(Integer id) {

        return roleMapper.deleteRoleById(id);
    }

    @Override
    public Integer deleteRolePermissionsByRoleId(Integer roleId) {

        return roleMapper.deleteRolePermissionsByRoleId(roleId);
    }

    @Override
    public Integer deleteRoleAccountsByRoleId(Integer roleId) {

        return roleMapper.deleteRoleAccountsByRoleId(roleId);
    }

    @Override
    public Integer getValidRoleCount() {

        return roleMapper.getValidRoleCount();
    }
}
