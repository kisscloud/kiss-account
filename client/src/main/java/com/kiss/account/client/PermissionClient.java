package com.kiss.account.client;


import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.UpdatePermissionInput;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionOutput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping()
public interface PermissionClient {

    @PostMapping("/permission")
    PermissionOutput createPermission(CreatePermissionInput createPermissionInput) throws NoSuchFieldException, IllegalAccessException;

    @GetMapping("/permissions")
    List<PermissionOutput> getPermissions();

    @GetMapping("/bind/permissions")
    List<BindPermissionOutput> getbindPermissions();

    @PutMapping("/permission")
    PermissionOutput updatePermission(UpdatePermissionInput updatePermissionInput);

    @DeleteMapping("/permission")
    void deletePermission(@RequestParam("id") Integer id);

    @GetMapping("/permission/valid/count")
    Integer getValidPermissionsCount();
}
