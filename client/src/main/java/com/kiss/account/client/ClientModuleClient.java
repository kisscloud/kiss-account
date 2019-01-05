package com.kiss.account.client;

import com.kiss.account.input.UpdateClientModulesInput;
import com.kiss.account.output.ClientModuleOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface ClientModuleClient {

    @PutMapping("/clientModules")
    List<ClientModuleOutput> updateClientModules (@Validated @RequestBody UpdateClientModulesInput updateClientModulesInput);

    @GetMapping("/clientModules")
    List<ClientModuleOutput> getClientModules (@RequestParam("clientId") Integer clientId);
}
