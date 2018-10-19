package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface AccountGroupClient {
    @PostMapping("/account/group")
    ResultOutput<AccountGroupOutput> createAccountGroup(@Validated @RequestBody CreateAccountGroupInput createAccountGroupInput);

    @GetMapping("/groups")
    ResultOutput getGroups();

    @GetMapping("/group")
    ResultOutput getGroup(String id);

    @PutMapping("/account/group")
    ResultOutput updateAccountGroup(UpdateAccountGroupInput updateAccountGroupInput);
}
