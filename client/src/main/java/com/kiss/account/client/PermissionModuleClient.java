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
