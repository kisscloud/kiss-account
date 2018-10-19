package com.kiss.account.output;

import lombok.Data;

import java.util.List;

@Data
public class AuthOutput {
    private String accessToken;
    private Long expiredAt;
    private String name;
    private List<String> permissions;
}
