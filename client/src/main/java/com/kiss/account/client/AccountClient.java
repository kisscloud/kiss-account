package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface AccountClient {

    @PostMapping("/account/group")
    ResultOutput<AccountGroupOutput> createAccountGroup(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput);

    @PostMapping("/account")
    ResultOutput createAccount(@Validated @RequestBody CreateAccountInput createAccountInput);

    @PostMapping("/account/roles")
    ResultOutput bindAccountRoles(@Validated @RequestBody BindRoleToAccountInput bindRoleToAccountInput);

    @GetMapping("/accounts")
    ResultOutput<GetAccountsOutput> getAccounts(@RequestParam("page") String page, @RequestParam("size") String size);

    @GetMapping("/account")
    ResultOutput getAccount(String id);

    @GetMapping("/groups")
    ResultOutput getGroups();

    @GetMapping("/group")
    ResultOutput getGroup(String id);

    @GetMapping("/accounts/count")
    ResultOutput getAccountsCount();

    @GetMapping("/get")
    ResultOutput get();

    @PutMapping("/account")
    ResultOutput<AccountOutput> updateAccount(UpdateAccountInput updateAccountInput);

    @PutMapping("/account/group")
    ResultOutput updateAccountGroup(UpdateAccountGroupInput updateAccountGroupInput);

    @PutMapping("/account/password")
    ResultOutput updateAccountPassword(@RequestParam("id") Integer id);

    @PutMapping("/account/status")
    ResultOutput updateAccountStatus(@Validated @RequestBody UpdateAccountStatusInput updateAccountStatusInput);

}
