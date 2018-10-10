package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AccountRoles {
    /**
     * 账户ID
     */
    private int id;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 用户id
     */
    private int accountId;
    /**
     * 最后操作者ID
     */
    private int operatorId;
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
