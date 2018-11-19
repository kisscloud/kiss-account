package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizationTarget {

    private Integer id;

    private Integer clientId;

    private String ip;

    private String remark;

    private Date createdAt;

    private Date updatedAt;
}
