package com.kiss.account.dao;

import com.kiss.account.entity.AccountGroup;
import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermissions;
import com.kiss.account.mapper.RoleMapper;
import com.kiss.account.output.RoleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    public void createRole(Role role) {
        roleMapper.createRole(role);
    }

    public void allocatePermissionsToRole(List<RolePermissions> rolePermissions) {
        roleMapper.allocatePermissionsToRole(rolePermissions);
    }

    public void allocatePermissionToRole(RolePermissions rolePermissions) {
        roleMapper.allocatePermissionToRole(rolePermissions);
    }

    public List<Role> getRoles() {
        List<Role> roles = roleMapper.getRoles();
        return roles;
    }

    public List<Integer> getRolesPermissionIds(Integer id) {
        return roleMapper.getRolesPermissionIds(id);
    }

    public List<Integer> getRolesAccountIds(Integer id) {
        return roleMapper.getRolesAccountIds(id);
    }

    public Role getRoleByName (String name) {
        return roleMapper.getRoleByName(name);
    }

    public Integer putRole (RoleOutput roleOutput) {
        return roleMapper.putRole(roleOutput);
    }

    public Integer deleteRole (Integer id) {
        return roleMapper.deleteRole(id);
    }

    public Integer deleteRolePermissions (Integer id) {
        return roleMapper.deleteRolePermissions(id);
    }
}
