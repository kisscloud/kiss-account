package com.kiss.account.entity;

import java.util.Date;

public class Log {
    /**
     * 日志ID
     */
    private Integer id;
    /**
     * 日志类型
     */
    private Integer type;
    /**
     * 目标ID
     */
    private Integer targetId;
    /**
     * 原来的值
     */
    private String oldValue;
    /**
     * 更新之后的值
     */
    private String newValue;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 最后操作者ID
     */
    private Integer operatorId;
    /**
     * 最后操作者
     */
    private String operatorName;
    /**
     * 最后操作者IP
     */
    private String operatorIp;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
