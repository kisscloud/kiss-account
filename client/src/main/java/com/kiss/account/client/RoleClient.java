package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountRoleOutput;
import com.kiss.account.output.RoleOutput;
import com.kiss.account.output.RolePermissionOutput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping()
public interface RoleClient {

    @PostMapping("/role")
    RoleOutput createRole(@RequestBody CreateRoleInput createRoleInput);

    @PostMapping("/role/permissions")
    List<RolePermissionOutput> bindRolePermissions(@RequestBody BindPermissionToRoleInput bindPermissionToRoleInput);

    @GetMapping("/roles")
    List<RoleOutput> getRoles();

    /**
     * 获取角色绑定的权限列表
     *
     * @param id Integer 角色ID
     * @return RoleOutput
     */
    @GetMapping("/role/permissions")
    List<RolePermissionOutput> getRolePermissions(@RequestParam("id") Integer id);

    @GetMapping("/role/accountIds")
    List<Integer> getRoleAccountIds(@RequestParam("id") Integer id);

    @PostMapping("/role/accounts")
    List<AccountRoleOutput> bindRoleAccounts(@RequestBody BindAccountsToRoleInput bindAccountsToRoleInput);

    @PutMapping("/role")
    RoleOutput updateRole(UpdateRoleInput updateRoleInput);

    @DeleteMapping("/role")
    void deleteRole(@RequestParam("id") Integer id);

    @GetMapping("/roles/valid/count")
    Integer getValidRolesCount();
}
