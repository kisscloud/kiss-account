package com.kiss.account.client;


import com.kiss.account.input.*;
import com.kiss.account.output.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
public interface ClientClient {

    @GetMapping("/clients")
    List<ClientOutput> getClients();

    @GetMapping("/client")
    ClientOutput getClient(@RequestParam("id") Integer id);

    @PostMapping("/client")
    ClientOutput createClient(@Validated @RequestBody CreateClientInput clientInput);

    @PutMapping("/client")
    ClientOutput updateClient(@Validated @RequestBody UpdateClientInput updateClientInput);

    @DeleteMapping("/client")
    void deleteClient(@RequestParam("id") Integer id);

    @PostMapping("/client/secret")
    String getClientSecret(@RequestBody GetClientSecretInput getClientSecretInput);

    @PostMapping("/client/authorization")
    AuthOutput ClientAuthorization(@Validated @RequestBody ClientAuthorizationInput clientAuthorizationInput);

    @PostMapping("/client/accounts")
    List<ClientAccountOutput> getClientAccounts(@Validated @RequestBody ClientAccountInput clientAccountInput);

    @PostMapping("/client/authorization/target")
    AuthorizationTargetOutput createClientAuthorizationTarget(@Validated @RequestBody AuthorizationTargetInput authorizationTargetInput);

    @GetMapping("/client/authorization/target")
    List<AuthorizationTargetOutput> getClientAuthorizationTarget(@RequestParam("clientId") Integer clientId);

    @PostMapping("/client/webhook")
    WebHookOutput createWebHook(@Validated @RequestBody CreateWebHookInput webHookInput);

    @DeleteMapping("/client/webhook")
    void deleteWebHook(@RequestParam("id") Integer id);

    @PutMapping("/client/webhook")
    WebHookOutput updateWebHook(@Validated @RequestBody UpdateWebHookInput updateWebHookInput);

    @GetMapping("/client/webhook")
    List<WebHookOutput> getWebHooks(@RequestParam("clientId") Integer clientId,Integer id);
}
