package com.kiss.account.input;

import lombok.Data;

import java.util.List;

@Data
public class AllocateAccountsToRoleInput {

    private List<Integer> accountIds;
    private Integer id;
}
