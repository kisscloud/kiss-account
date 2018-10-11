package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    /**
     * 账户ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String email;
    /**
     * 邮箱
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码盐
     */
    private String salt;
    /**
     * 账户备注
     */
    private String remark;
    /**
     * 所属分组ID
     */
    private Integer groupId;
    /**
     * 账户状态
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
