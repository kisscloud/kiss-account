package com.kiss.account.input;

import lombok.Data;

@Data
public class UpdateClientInput {
    private Integer id;

    private String clientName;

    private Integer status;
}
