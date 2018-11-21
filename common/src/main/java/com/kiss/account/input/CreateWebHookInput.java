package com.kiss.account.input;

import lombok.Data;

@Data
public class CreateWebHookInput {

    private Integer clientId;

    private String url;
}
