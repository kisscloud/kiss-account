package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PutAccountInput {
    @NotNull(message = "用户id不能为空")
    private Integer id;
    private String username;
    private String email;
    private String mobile;
    private String name;
    private Integer groupId;
}
