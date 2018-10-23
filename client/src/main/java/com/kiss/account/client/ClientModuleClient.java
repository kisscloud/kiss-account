package com.kiss.account.client;

import com.kiss.account.input.CreateClientModulesInput;
import com.kiss.account.input.UpdateClientModulesInput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface ClientModuleClient {

    @PostMapping("/clientModules")
    ResultOutput createClientModules (@Validated @RequestBody CreateClientModulesInput createClientModulesInput);

    @PutMapping("/clientModules")
    ResultOutput updateClientModules (@Validated @RequestBody UpdateClientModulesInput updateClientModulesInput);
}
