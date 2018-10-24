package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

@RequestMapping
public interface AccountGroupClient {
    @PostMapping("/account/group")
    ResultOutput createAccountGroup(CreateAccountGroupInput createAccountGroupInput);

    @GetMapping("/groups")
    ResultOutput getGroups();

    @GetMapping("/group")
    ResultOutput getGroup(Integer id);

    @DeleteMapping("/group")
    ResultOutput deleteGroup(@RequestParam("id") Integer id);

    @PutMapping("/account/group")
    ResultOutput updateAccountGroup(UpdateAccountGroupInput updateAccountGroupInput);

    @GetMapping("/account/groups/count")
    ResultOutput getAccountGroupCount();
}
