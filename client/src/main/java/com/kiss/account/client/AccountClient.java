package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface AccountClient {

    @PostMapping("/account")
    ResultOutput createAccount(@Validated @RequestBody CreateAccountInput createAccountInput);

    @PutMapping("/account")
    ResultOutput updateAccount(@Validated @RequestBody UpdateAccountInput updateAccountInput);

    @PostMapping("/account/roles")
    ResultOutput bindAccountRoles(@Validated @RequestBody BindRoleToAccountInput bindRoleToAccountInput);

    @GetMapping("/accounts")
    ResultOutput<GetAccountsOutput> getAccounts(@RequestParam("page") String page, @RequestParam("size") String size);

    @GetMapping("/account")
    ResultOutput getAccount(String id);

    @GetMapping("/accounts/count")
    ResultOutput getAccountsCount();

    @PutMapping("/account/password")
    ResultOutput updateAccountPassword(@RequestParam("id") Integer id);

    @PutMapping("/account/status")
    ResultOutput updateAccountStatus(@Validated @RequestBody UpdateAccountStatusInput updateAccountStatusInput);

    @RequestMapping(value = "/account/permissions", method = RequestMethod.GET)
    ResultOutput getAccountPermissions(@RequestParam("id") Integer id);

    @RequestMapping(value = "/account/permissions/dataScope", method = RequestMethod.GET)
    ResultOutput getAccountPermissionDataScope(@RequestParam("id") Integer id, @RequestParam("code") String code);

    @GetMapping("/accounts/valid/count")
    ResultOutput getValidAccountsCount();
}
