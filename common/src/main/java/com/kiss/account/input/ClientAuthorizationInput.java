package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ClientAuthorizationInput {

    @NotEmpty(message = "授权码不能为空")
    private String code;

    @NotEmpty(message = "客户端id不能为空")
    private String clientId;

    @NotEmpty(message = "客户端密钥不能为空")
    private String secret;

    @NotEmpty(message = "有效时间不能为空")
    private long expired;
}
