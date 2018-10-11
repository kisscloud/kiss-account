package com.kiss.account.client;

import com.kiss.account.input.AllocateRoleToAccountInput;
import com.kiss.account.input.CreateAccountGroupInput;
import com.kiss.account.input.CreateAccountInput;
import com.kiss.account.output.ResultOutput;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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
    String get();
}
