package com.kiss.account.client;

import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface PermissionModuleClient {

    @PostMapping("/permission/module")
    ResultOutput createPermissionModule(CreatePermissionModuleInput permissionModuleInput);

    @GetMapping("/permission/modules")
    ResultOutput getPermissionModules();

    @GetMapping("/bind/permission/modules")
    ResultOutput getBindPermissionModules();

    @PutMapping("/permission/module")
    ResultOutput updatePermissionModule(UpdatePermissionModuleInput updatePermissionModuleInput);

    @DeleteMapping("/permission/module")
    ResultOutput deletePermissionModule(@RequestParam("id") Integer id);
}
