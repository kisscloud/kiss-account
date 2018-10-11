package com.kiss.account.client;

import com.kiss.account.input.AllocateRoleToAccountInput;
import com.kiss.account.input.CreateAccountGroupInput;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.output.ResultOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping
public interface AccountClient {

    @PostMapping("/accounts/groups")
    ResultOutput postAccountGroups(CreateAccountGroupInput createAccountGroupInput);

    @PostMapping("/accounts")
    ResultOutput postAccounts(@RequestBody CreateAccountInput createAccountInput);

    @PostMapping("/accounts/role")
    ResultOutput postAccountsRole(@RequestBody AllocateRoleToAccountInput allocateRoleToAccount);

    @GetMapping("/accounts")
    ResultOutput getAccounts(HttpServletRequest request, HttpServletResponse response);

    @GetMapping("/account")
    ResultOutput getAccount(HttpServletRequest request, HttpServletResponse response);

    @GetMapping("/groups")
    ResultOutput getGroups(HttpServletRequest request, HttpServletResponse response);

    @GetMapping("/group")
    ResultOutput getGroup(HttpServletRequest request, HttpServletResponse response);

    @GetMapping("/accounts/count")
    ResultOutput getAccountsCount(HttpServletRequest request, HttpServletResponse response);

    @GetMapping("/get")
    String get();
}
