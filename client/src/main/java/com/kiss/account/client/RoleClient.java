package com.kiss.account.client;

import com.kiss.account.input.AllocatePermissionToRoleInput;
import com.kiss.account.output.RoleOutput;
import com.kiss.account.input.CreateRoleInput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import java.util.List;

@RequestMapping()
public interface RoleClient {

    @PostMapping("/roles")
    ResultOutput<RoleOutput> postRoles(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/roles/permissions")
    ResultOutput postRolesPermissions(@RequestBody AllocatePermissionToRoleInput allocatePermissionToRole);

    @GetMapping("/roles")
    ResultOutput<List<RoleOutput>> getRoles();

    @GetMapping("/roles/permissionIds")
    ResultOutput<List<Integer>> getRolesPermissionIds(@RequestParam("id") Integer id);
}
