package com.kiss.account.entity;

import lombok.Data;

@Data
public class ServiceStatus {
    private int id;
    private String statusName;
    private int status;
    private String language;
    private String value;
}
