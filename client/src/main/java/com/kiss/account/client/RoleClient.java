package com.kiss.account.client;

import com.kiss.account.input.BindAccountsToRoleInput;
import com.kiss.account.input.BindPermissionToRoleInput;
import com.kiss.account.input.UpdateRoleInput;
import com.kiss.account.output.AccountRoleOutput;
import com.kiss.account.output.RoleOutput;
import com.kiss.account.input.CreateRoleInput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import java.util.List;

@RequestMapping()
public interface RoleClient {

    @PostMapping("/role")
    ResultOutput createRole(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/roles/permissions")
    ResultOutput bindRolePermissions(@RequestBody BindPermissionToRoleInput bindPermissionToRoleInput);

    @GetMapping("/roles")
    ResultOutput<List<RoleOutput>> getRoles();

    @GetMapping("/role/permissionIds")
    ResultOutput<List<Integer>> getRolePermissionIds(@RequestParam("id") Integer id);

    @GetMapping("/role/accountIds")
    ResultOutput<List<Integer>> getRoleAccountIds(@RequestParam("id") Integer id);

    @PostMapping("/role/accounts")
    ResultOutput<List<AccountRoleOutput>> bindRoleAccounts(@RequestBody BindAccountsToRoleInput bindAccountsToRoleInput);

    @PutMapping("/role")
    ResultOutput updateRole(UpdateRoleInput updateRoleInput);

    @DeleteMapping("/role")
    ResultOutput deleteRole(@RequestParam("id") Integer id);
}
