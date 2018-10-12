package com.kiss.account.client;

import com.kiss.account.input.AllocatePermissionToRoleInput;
import com.kiss.account.input.CreateRoleInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;
@RequestMapping()
public interface RoleClient {

    @PostMapping("/roles")
    ResultOutput postRoles(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/roles/permissions")
    ResultOutput postRolesPermissions(@RequestBody AllocatePermissionToRoleInput allocatePermissionToRole);
}
