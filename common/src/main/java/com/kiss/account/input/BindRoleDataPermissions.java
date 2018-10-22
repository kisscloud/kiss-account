package com.kiss.account.input;

import lombok.Data;

@Data
public class BindRoleDataPermissions {

    private Integer roleId;

    private Integer permissionId;

    private String dataCode;

    private String dataDesc;
}
