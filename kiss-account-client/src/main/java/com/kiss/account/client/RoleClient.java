package com.kiss.account.client;

import com.kiss.account.input.AllocatePermissionToRoleInput;
import com.kiss.account.input.CreateRoleInput;
import com.kiss.account.output.ResultOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/roles")
public interface RoleClient {

    @PostMapping("/create")
    ResultOutput create(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/role/permissions")
    ResultOutput allocatePermissionsToRole(@RequestBody AllocatePermissionToRoleInput allocatePermissionToRole);
}
