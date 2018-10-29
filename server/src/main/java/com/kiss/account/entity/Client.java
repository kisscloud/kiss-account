package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Client {

    /**
     * 客户端id
     */
    private Integer id;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端内部id
     */
    private String clientId;

    /**
     * 客户端secret
     */
    private String clientSecret;

    /**
     * 最后登录时间
     */
    private Date lastAccessAt;

    /**
     * 客户端是否有效
     */
    private Integer status;

    /**
     * 最后操作者ID
     */
    private Integer operatorId;

    /**
     * 最后添加者姓名
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
