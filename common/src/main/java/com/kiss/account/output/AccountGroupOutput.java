package com.kiss.account.output;

import lombok.Data;

import java.util.Date;

@Data
public class AccountGroupOutput {
    /**
     * 分组ID
     */
    private Integer id;
    /**
     * 分组名称
     */
    private String name;
    /**
     * 分组层级
     */
    private String level;
    /**
     * 分组排序，由小到大
     */
    private Integer seq;
    /**
     * 分组备注
     */
    private String remark;
    /**
     * 父分组ID
     */
    private Integer parentId;
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
