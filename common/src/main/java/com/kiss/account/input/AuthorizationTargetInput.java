package com.kiss.account.input;

import lombok.Data;

@Data
public class AuthorizationTargetInput {

    private Integer clientId;

    private String ip;

    private String remark;
}
