package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreatePermissionModuleInput {
    @NotEmpty(message = "模块名不能为空")
    private String name;

    @NotNull(message = "父模块名不能为空")
    private Integer parentId;

    private String remark;
}
