package com.kiss.account.client;

import com.kiss.account.input.AllocateRoleToAccountInput;
import com.kiss.account.input.CreateAccountGroupInput;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.output.AccountGroupOutput;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import java.util.List;

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
}
