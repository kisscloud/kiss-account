package com.kiss.account.client;

import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.input.UpdatePermissionModuleInput;
import com.kiss.account.output.PermissionModuleOutput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface PermissionModuleClient {

    @PostMapping("/permission/module")
    PermissionModuleOutput createPermissionModule(CreatePermissionModuleInput permissionModuleInput);

    @GetMapping("/permission/modules")
    List<PermissionModuleOutput>  getPermissionModules();

    @GetMapping("/bind/permission/modules")
    List<PermissionModuleOutput> getBindPermissionModules();

    @PutMapping("/permission/module")
    PermissionModuleOutput updatePermissionModule(UpdatePermissionModuleInput updatePermissionModuleInput);

    @DeleteMapping("/permission/module")
    void deletePermissionModule(@RequestParam("id") Integer id);
}
