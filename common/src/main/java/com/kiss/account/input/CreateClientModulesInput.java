package com.kiss.account.input;

import lombok.Data;

import java.util.List;

@Data
public class CreateClientModulesInput {

    private Integer clientId;

    private List<Integer> moduleIds;
}
