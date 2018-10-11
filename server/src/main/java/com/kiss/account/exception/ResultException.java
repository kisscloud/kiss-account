package com.kiss.account.exception;


import com.kiss.account.enums.ResultOutputCodeEnum;

public class ResultException extends RuntimeException {

    private Integer code;

    public ResultException(Exception e) {
        super(e.getMessage());
        code = ResultOutputCodeEnum.SystemError.getCode();
        e.printStackTrace();
    }



}
