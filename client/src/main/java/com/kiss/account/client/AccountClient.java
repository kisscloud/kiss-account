package com.kiss.account.client;

import com.kiss.account.input.*;
import com.kiss.account.output.GetAccountsOutput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import output.ResultOutput;

import javax.naming.InvalidNameException;

@RequestMapping
public interface AccountClient {

    @PostMapping("/account")
    ResultOutput createAccount(@Validated @RequestBody CreateAccountInput createAccountInput) throws InvalidNameException;

    @GetMapping("/root/check")
    ResultOutput checkRoot(@Validated @RequestBody CreateAccountInput createAccountInput) throws InvalidNameException;

    @PostMapping("/root")
    ResultOutput createRoot(@Validated @RequestBody CreateAccountInput createAccountInput) throws InvalidNameException;

    @PutMapping("/account")
    ResultOutput updateAccount(@Validated @RequestBody UpdateAccountInput updateAccountInput) throws InvalidNameException;

    @GetMapping("/accounts")
    ResultOutput<GetAccountsOutput> getAccounts(@RequestParam("page") String page, @RequestParam("size") String size);

    @GetMapping("/account")
    ResultOutput getAccount(String id);

    @GetMapping("/accounts/count")
    ResultOutput getAccountsCount();

    @PutMapping("/account/password/reset")
    ResultOutput resetAccountPassword(@RequestParam("id") Integer id) throws InvalidNameException;

    @PutMapping("/account/password")
    ResultOutput updateAccountPassword(@Validated @RequestBody UpdateAccountPasswordInput updateAccountPasswordInput) throws InvalidNameException;

    @PutMapping("/account/status")
    ResultOutput updateAccountStatus(@Validated @RequestBody UpdateAccountStatusInput updateAccountStatusInput) throws InvalidNameException;

    @RequestMapping(value = "/account/permissions", method = RequestMethod.GET)
    ResultOutput getAccountPermissions(@RequestParam("id") Integer id);

    @RequestMapping(value = "/account/permissions/dataScope", method = RequestMethod.GET)
    ResultOutput getAccountPermissionDataScope(@RequestParam("id") Integer id, @RequestParam("code") String code);

    @GetMapping("/accounts/valid/count")
    ResultOutput getValidAccountsCount();

    @PostMapping("/account/validated")
    ResultOutput validateAccount(@RequestBody ValidateAccountInput validateAccountInput);
}
