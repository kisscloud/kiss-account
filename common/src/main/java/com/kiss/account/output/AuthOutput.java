package com.kiss.account.output;

import lombok.Data;

@Data
public class AuthOutput {
    private String accessToken;
    private Long expiredAt;
    private String name;
}
