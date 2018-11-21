package com.kiss.account.output;

import lombok.Data;

@Data
public class WebHookOutput {

    private Integer id;

    private Integer clientId;

    private String url;

    private Integer operatorId;

    private String operatorName;

    private Long createdAt;

    private Long updatedAt;
}
