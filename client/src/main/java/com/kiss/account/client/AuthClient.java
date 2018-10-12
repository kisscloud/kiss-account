package com.kiss.account.client;

import com.kiss.account.input.LoginInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;
@RequestMapping("/auth")
public interface AuthClient {

    @PostMapping("/username/login")
    ResultOutput loginWithUsernameAndPassword(@RequestBody LoginInput loginInput);
}
