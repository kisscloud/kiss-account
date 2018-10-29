package com.kiss.account.output;

import lombok.Data;

import java.util.Date;

@Data
public class ClientOutput {

    private Integer id;

    private String clientName;

    private String clientId;

    private String clientSecret;

    private Date lastAccessAt;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;
}
