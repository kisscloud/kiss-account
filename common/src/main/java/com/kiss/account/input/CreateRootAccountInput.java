package com.kiss.account.input;

import lombok.Data;

@Data
public class CreateRootAccountInput {

    private String username;

    private String email;

    private String mobile;

    private String password;

    private String name;

    private Integer status;

    private String remark;
}
