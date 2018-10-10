package com.kiss.account.service;

import com.kiss.account.client.RoleClient;
import com.kiss.account.dao.RoleDao;
import com.kiss.account.entity.Role;
import com.kiss.account.entity.RolePermissions;
import com.kiss.account.input.AllocatePermissionToRoleInput;
import com.kiss.account.input.CreateRoleInput;
import com.kiss.account.output.ResultOutput;
import com.kiss.account.utils.ResultOutputUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleServiceImpl implements RoleClient {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ResultOutput create(CreateRoleInput createRoleInput) {
        Role role = new Role();
        BeanUtils.copyProperties(createRoleInput,role);
        role.setOperatorId(123);
        role.setOperatorIp("127.0.0.5");
        role.setOperatorName("旺旺");
        roleDao.createRole(role);
        return ResultOutputUtil.success();
    }

    @Override
    public ResultOutput allocatePermissionsToRole(AllocatePermissionToRoleInput allocatePermissionToRole) {
        List<Integer> permissions = allocatePermissionToRole.getPermissionId();
        List<RolePermissions> rolePermissions = new ArrayList<>();
        for (Integer permissionId : permissions) {
            RolePermissions rolePermission = new RolePermissions();
            rolePermission.setRoleId(allocatePermissionToRole.getRoleId());
            rolePermission.setOperatorId(123);
            rolePermission.setOperatorIp("127.0.0.5");
            rolePermission.setOperatorName("旺旺");
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        roleDao.allocatePermissionsToRole(rolePermissions);
        return ResultOutputUtil.success();
    }

}
