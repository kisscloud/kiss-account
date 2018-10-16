package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BindPermissionToRoleInput {

    @NotNull(message = "角色ID不能为空")
    private int roleId;

    private List<Integer> permissions;
}
