package com.kiss.account.input;

import lombok.Data;

import java.util.List;

@Data
public class AllocateRoleToAccountInput {
    private List<Integer> roleId;

    private int accountId;
}
