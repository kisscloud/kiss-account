package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OperationLog {

    private Integer id;

    private Integer operatorId;

    private String operatorName;

    private String operatorIp;

    private Integer targetType;

    private Integer targetId;

    private String beforeValue;

    private String afterValue;

    private Date recoveredAt;

    private Date createdAt;

    private Date updatedAt;
}
