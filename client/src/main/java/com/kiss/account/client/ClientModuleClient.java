package com.kiss.account.client;

import com.kiss.account.input.UpdateClientModulesInput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface ClientModuleClient {

    @PutMapping("/clientModules")
    ResultOutput updateClientModules (@Validated @RequestBody UpdateClientModulesInput updateClientModulesInput);

    @GetMapping("/clientModules")
    ResultOutput getClientModules (@RequestParam("clientId") Integer clientId);
}
