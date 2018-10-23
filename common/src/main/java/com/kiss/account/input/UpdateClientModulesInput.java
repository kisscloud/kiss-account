package com.kiss.account.input;

import lombok.Data;

import java.util.List;

@Data
public class UpdateClientModulesInput {

    private Integer clientId;

    private List<Integer> moduleIds;
}
