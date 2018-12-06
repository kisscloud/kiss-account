package com.kiss.account.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateAccountPasswordInput {

    private Integer id;
    private String oldPassword;
    private String newPassword;
}
