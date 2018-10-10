package com.kiss.account.output;

import lombok.Data;

@Data
public class ResultOutput<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;
}
