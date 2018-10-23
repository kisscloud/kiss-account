package com.kiss.account.input;

import lombok.Data;

@Data
public class UpdateClientInput {
    private String clientID;

    private String clientName;

    private Integer status;
}
