package com.kiss.account.input;

import lombok.Data;

/**
 * 创建账户输入
 */
@Data
public class CreateAccountInput {

    private String username;

    private String email;

    private String mobile;

    private String password;

    private String name;

    private Integer groupId;

    private Integer status;

    private String remark;
}
