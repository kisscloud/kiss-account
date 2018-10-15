package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PutAccountInput {
    @NotEmpty(message = "用户id不能为空")
    private Integer id;
    private String username;
    private String email;
    private String mobile;
    private String name;
    private Integer groupId;
}
