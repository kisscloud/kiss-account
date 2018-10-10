package com.kiss.account.input;

import lombok.Data;

import java.util.List;

@Data
public class AllocatePermissionToRoleInput {
    private int roleId;
    private List<Integer> permissionId;
}
