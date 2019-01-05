package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountGroupOutput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface AccountGroupClient {
    @PostMapping("/account/group")
    AccountGroupOutput createAccountGroup(CreateAccountGroupInput createAccountGroupInput);

    @GetMapping("/groups")
    List<AccountGroupOutput> getGroups();

    @GetMapping("/group")
    AccountGroupOutput getGroup(Integer id);

    @DeleteMapping("/group")
    void deleteGroup(@RequestParam("id") Integer id);

    @PutMapping("/account/group")
    AccountGroupOutput updateAccountGroup(UpdateAccountGroupInput updateAccountGroupInput);

    @GetMapping("/account/groups/count")
    Integer getAccountGroupsCount();
}
