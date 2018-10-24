package com.kiss.account.entity;

import lombok.Data;

@Data
public class DbEnums {

    /**
     * 主键id
     */
    private int id;

    /**
     * 操作的key
     */
    private String key;

    /**
     * 数据库存储值
     */
    private int option;

    /**
     * 语言
     */
    private String language;

    /**
     * 操作文案
     */
    private String value;
}
