package com.kiss.account.entity;

import lombok.Data;

@Data
public class ClientModule {

    /**
     * 客户端模块id
     */
    private Integer id;

    /**
     * 客户端id
     */
    private Integer clientId;

    /**
     * 模块id
     */
    private Integer moduleId;
}
