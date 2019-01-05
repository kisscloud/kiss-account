package com.kiss.account.client;

import com.kiss.account.input.LoginInput;
import com.kiss.account.output.AuthOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthClient {

    @PostMapping("/username/login")
    AuthOutput loginWithUsernameAndPassword(@RequestBody LoginInput loginInput);
}
