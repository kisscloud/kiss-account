package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Client {

    private Integer id;

    private String clientName;

    private String clientID;

    private String clientSecret;

    private Date lastAccessAt;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;
}
