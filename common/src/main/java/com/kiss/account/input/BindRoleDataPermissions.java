package com.kiss.account.input;

import lombok.Data;

@Data
public class BindRoleDataPermissions {

    private Integer permissionId;

    private String limitString;

    private String limitDescription;
}
