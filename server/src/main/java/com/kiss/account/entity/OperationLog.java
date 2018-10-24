package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OperationLog {

    /**
     * 操作日志id
     */
    private Integer id;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 操作人ip
     */
    private String operatorIp;

    /**
     * 操作类型
     */
    private Integer targetType;

    /**
     * 操作id
     */
    private Integer targetId;

    /**
     * 操作前的值
     */
    private String beforeValue;

    /**
     * 操作后的值
     */
    private String afterValue;

    /**
     * 回滚时间
     */
    private Date recoveredAt;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}
