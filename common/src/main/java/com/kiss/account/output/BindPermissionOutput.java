package com.kiss.account.output;

import lombok.Data;

@Data
public class BindPermissionOutput {
    /**
     * 权限ID
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限所属模块ID
     */
    private Integer moduleId;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 模块父ID
     */
    private Integer moduleParentId;
    /**
     * 权限类型
     */
    private Integer type;
    /**
     * 权限码
     */
    private String code;
    /**
     * 权限限制数据字段
     */
    private String limitFields;
    /**
     * 权限状态
     */
    private Integer status;
    /**
     * 权限排序
     */
    private Integer seq;
    /**
     * 权限备注
     */
    private String remark;
}
