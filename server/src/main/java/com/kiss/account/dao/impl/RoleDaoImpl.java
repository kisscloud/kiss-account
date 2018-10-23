package com.kiss.account.dao.impl;

import com.kiss.account.dao.RoleDao;
import com.kiss.account.entity.Permission;
import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermission;
import com.kiss.account.mapper.RoleMapper;
import com.kiss.account.output.RoleOutput;
import com.kiss.account.output.RolePermissionOutput;
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
        List<Role> roles = roleMapper.getRoles();
        return roles;
    }

    @Override
    public List<Integer> getRolesPermissionIds(Integer id) {
        return roleMapper.getRolesPermissionIds(id);
    }

    @Override
    public List<RolePermission> getRolePermissions(Integer id) {
        return roleMapper.getRolesPermissions(id);
    }

    @Override
    public List<Integer> getRolesAccountIds(Integer id) {
        return roleMapper.getRolesAccountIds(id);
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
    public Integer putRole(RoleOutput roleOutput) {
        return roleMapper.putRole(roleOutput);
    }

    @Override
    public Integer deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public Integer deleteRolePermissions(Integer id) {
        return roleMapper.deleteRolePermissions(id);
    }

    @Override
    public Integer deleteRoleAccounts(Integer id) {
        return roleMapper.deleteRoleAccounts(id);
    }

    @Override
    public Integer getValidRoleCount() {
        return roleMapper.getValidRoleCount();
    }
}
