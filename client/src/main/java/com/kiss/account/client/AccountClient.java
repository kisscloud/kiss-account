package com.kiss.account.client;

import com.kiss.account.input.AllocateRoleToAccountInput;
import com.kiss.account.input.CreateAccountGroupInput;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.output.AccountGroupOutput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface AccountClient {

    @PostMapping("/accounts/groups")
    ResultOutput<AccountGroupOutput> postAccountGroups(CreateAccountGroupInput createAccountGroupInput);

    @PostMapping("/accounts")
    ResultOutput postAccounts(@RequestBody CreateAccountInput createAccountInput);

    @PostMapping("/accounts/role")
    ResultOutput postAccountsRole(@RequestBody AllocateRoleToAccountInput allocateRoleToAccount);

    @GetMapping("/accounts")
    ResultOutput getAccounts(@RequestParam("page") String page, @RequestParam("size") String size);

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
}
