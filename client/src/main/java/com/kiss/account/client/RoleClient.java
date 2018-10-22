package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountRoleOutput;
import com.kiss.account.output.RoleOutput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import java.util.List;

@RequestMapping()
public interface RoleClient {

    @PostMapping("/role")
    ResultOutput createRole(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/role/permissions")
    ResultOutput bindRolePermissions(@RequestBody BindPermissionToRoleInput bindPermissionToRoleInput);

    @GetMapping("/roles")
    ResultOutput getRoles();

    /**
     * 获取角色绑定的权限列表
     *
     * @param id Integer 角色ID
     * @return RoleOutput
     */
    @GetMapping("/role/permissions")
    ResultOutput getRolePermissions(@RequestParam("id") Integer id);

    @GetMapping("/role/accountIds")
    ResultOutput getRoleAccountIds(@RequestParam("id") Integer id);

    @PostMapping("/role/accounts")
    ResultOutput bindRoleAccounts(@RequestBody BindAccountsToRoleInput bindAccountsToRoleInput);

    @PutMapping("/role")
    ResultOutput updateRole(UpdateRoleInput updateRoleInput);

    @DeleteMapping("/role")
    ResultOutput deleteRole(@RequestParam("id") Integer id);

    @PostMapping("/role/dataPermissions")
    ResultOutput bindRoleDataPermissions(BindRoleDataPermissions bindRoleDataPermissions);
}
