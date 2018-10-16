package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePermissionModuleInput {

    @NotNull(message = "模块id不能为空")
    private Integer id;

    @NotEmpty(message = "模块名不能为空")
    private String name;

    @NotNull(message = "父模块名不能为空")
    private Integer parentId;

    private Integer status;
}
