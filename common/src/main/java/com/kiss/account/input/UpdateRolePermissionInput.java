package com.kiss.account.input;

import lombok.Data;

@Data
public class UpdateRolePermissionInput {
    private Integer roleId;
    private String permissionId;
    private String limitScope;
}
