package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreatePermissionInput {

    @NotEmpty(message = "PERMISSION_CODE_IS_NOT_EMPTY")
    private String code;

    @NotEmpty(message = "权限名称不能为空")
    private String name;

    @NotNull(message = "MODULE_ID_IS_NOT_NULL")
    private Integer moduleId;

    @NotNull(message = "权限类型不能为空")
    private Integer type;

    private Integer status;

    private Integer seq;

    private String remark;

    private String limitFields;
}
