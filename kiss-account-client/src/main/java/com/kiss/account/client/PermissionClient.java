package com.kiss.account.client;


import com.kiss.account.input.CreatePermissionInput;
import com.kiss.account.input.CreatePermissionModuleInput;
import com.kiss.account.output.ResultOutput;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/permissions")
public interface PermissionClient {

    @PostMapping
    ResultOutput create(@Validated @RequestBody CreatePermissionInput createPermissionInput, BindingResult bindingResult);

    @PostMapping("/modules")
    ResultOutput createModule(@Validated @RequestBody CreatePermissionModuleInput permissionModuleInput, BindingResult bindingResult);

    @GetMapping
    String list();


    @GetMapping("/{id}")
    String find();
}
