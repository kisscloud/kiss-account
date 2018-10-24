package com.kiss.account.entity;

import lombok.Data;

@Data
public class Guest {

    /**
     * 访客id
     */
    private Integer id;

    /**
     * 访客名称
     */
    private String name;

    /**
     * 访客ip
     */
    private String ip;
}
