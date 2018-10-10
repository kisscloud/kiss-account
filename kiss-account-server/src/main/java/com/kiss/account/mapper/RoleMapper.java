package com.kiss.account.mapper;

import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermissions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    void createRole(Role role);

    void allocatePermissionToRole(RolePermissions rolePermissions);

    void allocatePermissionsToRole(List<RolePermissions> rolePermissions);
}
