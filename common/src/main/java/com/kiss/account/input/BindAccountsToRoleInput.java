package com.kiss.account.input;

import lombok.Data;

import java.util.List;

@Data
public class BindAccountsToRoleInput {

    private List<Integer> accountIds;
    private Integer id;
}
