package com.kiss.account.exception;

public class TagException extends RuntimeException {

    private Integer code;

    public TagException(Integer code, String message) {

        super(message);
        this.code = code;
    }
}
