package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateAccountGroupInput {
    @NotEmpty(message = "部门编号不能为空")
    private Integer id;
    @NotEmpty(message = "部门名称不能为空")
    private String name;
}
