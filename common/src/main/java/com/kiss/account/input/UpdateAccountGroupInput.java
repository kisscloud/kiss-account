package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateAccountGroupInput {
    @NotNull(message = "部门编号不能为空")
    private Integer id;

    @NotNull(message = "父部门不能为空")
    private Integer parentId;

    @NotEmpty(message = "部门名称不能为空")
    private String name;
}
