package com.kiss.account.client;

import com.kiss.account.input.LoginInput;
import com.kiss.account.output.ResultOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface AuthClient {

    @PostMapping("/login")
    ResultOutput login(@RequestBody LoginInput loginInput);
}
