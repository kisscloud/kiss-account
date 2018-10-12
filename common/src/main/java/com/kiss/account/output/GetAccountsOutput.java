package com.kiss.account.output;

import lombok.Data;

import java.util.List;

@Data
public class GetAccountsOutput {
    private List<AccountOutput> accounts;

    private Integer count;

    public GetAccountsOutput(List<AccountOutput> accounts,Integer count) {
        this.accounts = accounts;
        this.count = count;
    }
}
