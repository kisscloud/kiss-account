package com.kiss.account.client;


import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;
@RequestMapping()
public interface PermissionClient {

    @PostMapping("/permissions")
    ResultOutput postPermissions(@Validated @RequestBody CreatePermissionInput createPermissionInput, BindingResult bindingResult);

    @PostMapping("/permissions/modules")
    ResultOutput postPermissionsModules(@Validated @RequestBody CreatePermissionModuleInput permissionModuleInput, BindingResult bindingResult);

    @GetMapping("/permissions")
    String getPermissions();


    @GetMapping("/permission")
    String getPermission();
}
