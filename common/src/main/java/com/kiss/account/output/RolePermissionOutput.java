package com.kiss.account.output;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionOutput {
    /**
     * 账户ID
     */
    private int id;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 操作权限id
     */
    private int permissionId;
    /**
     * 数据权限
     */
    private String limitScope;
    /**
     * 数据权限输入值
     */
    private String limitString;
    /**
     * 数据权限描述
     */
    private String limitDescription;
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
