package com.kiss.account.client;


import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.UpdatePermissionInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import java.util.List;

@RequestMapping()
public interface PermissionClient {

    @PostMapping("/permission")
    ResultOutput createPermission(CreatePermissionInput createPermissionInput);

    @PostMapping("/permission/module")
    ResultOutput createPermissionModule(CreatePermissionModuleInput permissionModuleInput);

    @GetMapping("/permissions")
    ResultOutput<List<PermissionOutput>> getPermissions();

    @GetMapping("/permission")
    ResultOutput<PermissionOutput> getPermission();

    @GetMapping("/bind/permissions")
    ResultOutput<List<BindPermissionOutput>> getbindPermissions();

    @GetMapping("/permission/modules")
    ResultOutput<List<PermissionModuleOutput>> getPermissionModules();

    @GetMapping("/bind/permission/modules")
    ResultOutput<List<PermissionModuleOutput>> getBindPermissionModules();

    @PutMapping("/permission")
    ResultOutput<PermissionOutput> updatePermission(UpdatePermissionInput updatePermissionInput);

    @PutMapping("/permission/module")
    ResultOutput<PermissionModuleOutput> updatePermissionModule(UpdatePermissionModuleInput updatePermissionModuleInput);

    @DeleteMapping("/permission")
    ResultOutput deletePermission(Integer id);

    @DeleteMapping("/permission/module")
    ResultOutput deletePermissionModule(Integer id);
}
