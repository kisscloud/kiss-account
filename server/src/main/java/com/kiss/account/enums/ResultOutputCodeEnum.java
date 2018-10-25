package com.kiss.account.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 */
@Getter
public enum ResultOutputCodeEnum {

    Success(0, "成功"),
    ValidateError(1, "数据校验错误"),
    SystemError(2, "系统错误"),
    DatabaseError(3, "系统错误");

    private Integer code;

    private String message;

    ResultOutputCodeEnum(Integer code, String message) {

        this.code = code;
        this.message = message;
    }
}
