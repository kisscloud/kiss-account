package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateRoleInput {
    @NotNull(message = "角色id不能为空")
    private Integer id;
    private String name;
    private Integer status;
}
