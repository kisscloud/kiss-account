package com.kiss.account.output;

import lombok.Data;

@Data
public class AuthorizationTargetOutput {

    private Integer id;

    private Integer clientId;

    private String ip;

    private String remark;

    private Long createdAt;

    private Long updatedAt;
}
