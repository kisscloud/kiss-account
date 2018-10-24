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

    @GetMapping("/permissions")
    ResultOutput<List<PermissionOutput>> getPermissions();

    @GetMapping("/permission")
    ResultOutput<PermissionOutput> getPermission();

    @GetMapping("/bind/permissions")
    ResultOutput<List<BindPermissionOutput>> getbindPermissions();

    @PutMapping("/permission")
    ResultOutput<PermissionOutput> updatePermission(UpdatePermissionInput updatePermissionInput);

    @DeleteMapping("/permission")
    ResultOutput deletePermission(@RequestParam("id") Integer id);

    @GetMapping("/permission/valid/count")
    ResultOutput getValidPermissionsCount();
}
