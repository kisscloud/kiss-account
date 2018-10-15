package com.kiss.account.client;


import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import java.util.List;

@RequestMapping()
public interface PermissionClient {

    @PostMapping("/permissions")
    ResultOutput postPermissions(CreatePermissionInput createPermissionInput);

    @PostMapping("/permissions/modules")
    ResultOutput postPermissionsModules(CreatePermissionModuleInput permissionModuleInput);

    @GetMapping("/permissions")
    ResultOutput<List<PermissionOutput>> getPermissions();

    @GetMapping("/permission")
    ResultOutput<PermissionOutput> getPermission();

    @GetMapping("/bind/permissions")
    ResultOutput<List<BindPermissionOutput>> getBindPermissions();

    @GetMapping("/permissions/modules")
    ResultOutput<List<PermissionModuleOutput>> getPermissionsModules();

    @GetMapping("/bind/permissions/modules")
    ResultOutput<List<PermissionModuleOutput>> getBindPermissionsModules();
}
