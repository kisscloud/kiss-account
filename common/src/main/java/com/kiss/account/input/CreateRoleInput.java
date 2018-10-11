package com.kiss.account.input;

import lombok.Data;

@Data
public class CreateRoleInput {
    private String name;
    private Integer type;
    private Integer status;
    private String remark;
}
