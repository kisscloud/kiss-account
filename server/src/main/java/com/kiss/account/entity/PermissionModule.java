package com.kiss.account.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PermissionModule {
    /**
     * 权限模块ID
     */
    private Integer id;
    /**
     * 权限模块名称
     */
    private String name;
    /**
     * 权限模块父ID
     */
    private Integer parentId;
    /**
     * 模块绑定的权限数
     */
    private Integer permissions;
    /**
     * 权限模块层级
     */
    private String level;
    /**
     * 权限模块排序，由小到大
     */
    private Integer seq;
    /**
     * 权限模块备注
     */
    private String remark;
    /**
     * 权限模块状态
     */
    private Integer status;
    /**
     * 最后操作者Id
     */
    private Integer operatorId;
    /**
     * 最后操作者Id
     */
    private String operatorName;
    /**
     * 最后操作IP
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
