package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateAccountStatusInput {
    @NotEmpty(message = "用户id不能为空")
    private Integer id;
    @NotEmpty(message = "用户状态不能为空")
    private Integer status;
}
