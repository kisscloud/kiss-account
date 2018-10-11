package com.kiss.account.utils;

import com.kiss.account.enums.ResultOutputCodeEnum;
import com.kiss.account.output.ResultOutput;

public class ResultOutputUtil {
    /**
     * 操作成功
     *
     * @param data Object
     * @return ResultOutput
     */
    public static ResultOutput success(Object data) {

        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setCode(ResultOutputCodeEnum.Success.getCode());
        resultOutput.setMessage("成功");
        resultOutput.setData(data);

        return resultOutput;
    }

    /**
     * 数据校验错误
     *
     * @param data Object
     * @return ResultOutput
     */
    public static ResultOutput validateError(Object data) {

        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setCode(ResultOutputCodeEnum.ValidateError.getCode());
        resultOutput.setMessage("数据校验错误");
        resultOutput.setData(data);

        return resultOutput;
    }

    public static ResultOutput error(int code,String message) {
        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setCode(code);
        resultOutput.setMessage(message);
        return resultOutput;
    }

    public static ResultOutput success() {

        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setCode(ResultOutputCodeEnum.Success.getCode());
        resultOutput.setMessage("成功");

        return resultOutput;
    }

}
