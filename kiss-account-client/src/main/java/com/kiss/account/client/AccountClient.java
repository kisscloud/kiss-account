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

@RequestMapping("/account")
public interface AccountClient {
    @RequestMapping("/create")
    ResultOutput create(CreateAccountGroupInput createAccountGroupInput);

    @PostMapping("/createAccount")
    ResultOutput create(@RequestBody CreateAccountInput createAccountInput);

    @PostMapping("/allocateRolesToAcount")
    ResultOutput allocateRolesToAcount(@RequestBody AllocateRoleToAccountInput allocateRoleToAccount);

    @GetMapping("/users")
    ResultOutput users(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @RequestMapping("/get")
    String get();
}
