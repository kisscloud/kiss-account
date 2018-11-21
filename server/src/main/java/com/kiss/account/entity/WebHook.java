package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WebHook {

    private Integer id;

    private Integer clientId;

    private String url;

    private Integer operatorId;

    private String operatorName;

    private Date createdAt;

    private Date updatedAt;

    public WebHook() {

    }

    public WebHook(Integer id) {
        this.id = id;
    }

    public WebHook(Integer id, Integer clientId) {
        this.id = id;
        this.clientId = clientId;
    }
}
