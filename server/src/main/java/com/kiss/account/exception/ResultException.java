package com.kiss.account.exception;


import com.kiss.account.enums.ResultOutputCodeEnum;

public class ResultException extends RuntimeException {

    private Integer code;

    public ResultException(Exception e) {
        super(e.getMessage());
        code = ResultOutputCodeEnum.SystemError.getCode();
        e.printStackTrace();
    }

    public ResultException (String message, Integer code) {
        super(message);
        this.code = code;
    }



}
