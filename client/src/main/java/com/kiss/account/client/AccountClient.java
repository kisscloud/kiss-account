package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.AccountOutput;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.Map;

@RequestMapping
public interface AccountClient {

    @PostMapping("/account")
    AccountOutput createAccount(@Validated @RequestBody CreateAccountInput createAccountInput) throws InvalidNameException;

    @GetMapping("/root/check")
    Map<String, Object> checkRoot();

    @PostMapping("/root")
    AccountOutput createRoot(@Validated @RequestBody CreateRootAccountInput createRootAccountInput);

    @PutMapping("/account")
    AccountOutput updateAccount(@Validated @RequestBody UpdateAccountInput updateAccountInput) throws InvalidNameException;

    @GetMapping("/accounts")
    GetAccountsOutput getAccounts(@RequestParam("page") String page, @RequestParam("size") String size);

    @GetMapping("/account")
    AccountOutput getAccount(String id);

    @GetMapping("/accounts/count")
    Integer getAccountsCount();

    @PutMapping("/account/password/reset")
    void resetAccountPassword(@RequestParam("id") Integer id) throws InvalidNameException;

    @PutMapping("/account/password")
    void updateAccountPassword(@Validated @RequestBody UpdateAccountPasswordInput updateAccountPasswordInput) throws InvalidNameException;

    @PutMapping("/account/status")
    AccountOutput updateAccountStatus(@Validated @RequestBody UpdateAccountStatusInput updateAccountStatusInput) throws InvalidNameException;

    @RequestMapping(value = "/account/permissions", method = RequestMethod.GET)
    List<String> getAccountPermissions(@RequestParam("id") Integer id);

    @RequestMapping(value = "/account/permissions/dataScope", method = RequestMethod.GET)
    List<String> getAccountPermissionDataScope(@RequestParam("id") Integer id, @RequestParam("code") String code);

    @GetMapping("/accounts/valid/count")
    Integer getValidAccountsCount();

    @PostMapping("/account/validated")
    void validateAccount(@RequestBody ValidateAccountInput validateAccountInput);

    @GetMapping("/account/name")
    AccountOutput getAccountById(@RequestParam("id") Integer id);
}
