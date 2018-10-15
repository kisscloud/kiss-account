package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface AccountClient {

    @PostMapping("/accounts/groups")
    ResultOutput<AccountGroupOutput> postAccountGroups(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput);

    @PostMapping("/accounts")
    ResultOutput postAccounts(@Validated @RequestBody CreateAccountInput createAccountInput);

    @PostMapping("/accounts/role")
    ResultOutput postAccountsRole(@Validated @RequestBody AllocateRoleToAccountInput allocateRoleToAccount);

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

    @PutMapping("/accounts")
    ResultOutput putAccount(@Validated @RequestBody PutAccountInput putAccountInput);

    @PutMapping("/accounts/group")
    ResultOutput putAccountGroup(@Validated @RequestBody PutAccountGroupInput putAccountGroupInput);

    @PutMapping("/accounts/password")
    ResultOutput putAccountPassword(@RequestParam("id") Integer id);

    @PutMapping("/accounts/status")
    ResultOutput putAccountStatus(@Validated @RequestBody PutAccountStatusInput putAccountStatusInput);

}
