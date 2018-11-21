package com.kiss.account.client;


import com.kiss.account.input.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface ClientClient {

    @GetMapping("/clients")
    ResultOutput getClients();

    @GetMapping("/client")
    ResultOutput getClient(@RequestParam("id") Integer id);

    @PostMapping("/client")
    ResultOutput createClient(@Validated @RequestBody CreateClientInput clientInput);

    @PutMapping("/client")
    ResultOutput updateClient(@Validated @RequestBody UpdateClientInput updateClientInput);

    @DeleteMapping("/client")
    ResultOutput deleteClient(@RequestParam("id") Integer id);

    @PostMapping("/client/secret")
    ResultOutput getClientSecret(@RequestBody GetClientSecretInput getClientSecretInput);

    @PostMapping("/client/authorization")
    ResultOutput ClientAuthorization(@Validated @RequestBody ClientAuthorizationInput clientAuthorizationInput);

    @PostMapping("/client/accounts")
    ResultOutput getClientAccounts(@Validated @RequestBody ClientAccountInput clientAccountInput);

    @PostMapping("/client/authorization/target")
    ResultOutput createClientAuthorizationTarget(@Validated @RequestBody AuthorizationTargetInput authorizationTargetInput);

    @GetMapping("/client/authorization/target")
    ResultOutput getClientAuthorizationTarget(@RequestParam("clientId") Integer clientId);

    @PostMapping("/client/webhook")
    ResultOutput createWebHook(@Validated @RequestBody CreateWebHookInput webHookInput);

    @DeleteMapping("/client/webhook")
    ResultOutput deleteWebHook(@RequestParam("id") Integer id);

    @PutMapping("/client/webhook")
    ResultOutput updateWebHook(@Validated @RequestBody UpdateWebHookInput updateWebHookInput);

    @GetMapping("/client/webhook")
    ResultOutput getWebHook(@RequestParam("clientId") Integer clientId,Integer id);
}
