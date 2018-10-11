package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    /**
     * 账户ID
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色类型
     */
    private Integer type;
    /**
     * 角色状态
     */
    private Integer status;
    /**
     * 角色备注
     */
    private String remark;

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
