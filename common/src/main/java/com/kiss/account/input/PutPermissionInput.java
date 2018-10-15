package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PutPermissionInput {
    @NotNull(message = "权限id不能为空")
    private Integer id;

    @NotEmpty(message = "权限码不能为空")
    private String code;

    @NotEmpty(message = "权限名称不能为空")
    private String name;

    @NotNull(message = "模块名称不能为空")
    private Integer moduleId;

    @NotNull(message = "权限类型不能为空")
    private Integer type;

    private Integer status;
}
