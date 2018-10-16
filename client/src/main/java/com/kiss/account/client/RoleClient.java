package com.kiss.account.client;

import com.kiss.account.input.AllocateAccountsToRoleInput;
import com.kiss.account.input.AllocatePermissionToRoleInput;
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
    ResultOutput<RoleOutput> createRole(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/roles/permissions")
    ResultOutput allocateRolePermissions(@RequestBody AllocatePermissionToRoleInput allocatePermissionToRole);

    @GetMapping("/roles")
    ResultOutput<List<RoleOutput>> getRoles();

    @GetMapping("/role/permissionIds")
    ResultOutput<List<Integer>> getRolePermissionIds(@RequestParam("id") Integer id);

    @GetMapping("/role/accountIds")
    ResultOutput<List<Integer>> getRoleAccountIds(@RequestParam("id") Integer id);

    @PostMapping("/role/accounts")
    ResultOutput<List<AccountRoleOutput>> allocateRoleAccounts(@RequestBody AllocateAccountsToRoleInput allocateAccountsToRoleInput);

    @PutMapping("/role")
    ResultOutput<RoleOutput> updateRole(UpdateRoleInput updateRoleInput);

    @DeleteMapping("/role")
    ResultOutput deleteRole(Integer id);
}
