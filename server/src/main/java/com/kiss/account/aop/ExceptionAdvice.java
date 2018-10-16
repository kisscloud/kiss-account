/**
 * @Title:
 * @Date 2018/7/6
 * @Description: TODO
 */
package com.kiss.account.aop;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.CodeUtil;
import com.kiss.account.utils.ResultOutputUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import output.ResultOutput;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @Title:
 * @author qianrongli
 * @Date 2018/10/16
 * @Description: TODO
 */
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResultOutput exceptionHandle(HttpServletRequest request, Exception e) {

        System.out.println("系统异常,接口："+request.getRequestURI());
        System.out.println(e.getMessage());
        System.out.println(e.getClass());

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodException = ((MethodArgumentNotValidException) e);
            BindingResult bindingResult = methodException.getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            try {
                String message = fieldError.getDefaultMessage();
                Field field = AccountStatusCode.class.getDeclaredField(message);
                Integer code = Integer.parseInt(field.get(field.getName()).toString());
                String language = StringUtils.isEmpty(request.getHeader("X-LANGUAGE")) ? "zh-CN" : request.getHeader("X-LANGUAGE");
                String returnMessage = CodeUtil.getMessage(language,code);
                return ResultOutputUtil.error(code,returnMessage,null);
            } catch (Exception e1) {
                return ResultOutputUtil.error(AccountStatusCode.REQUEST_PARAMETER_ERROR);
            }
        }
        return ResultOutputUtil.error(AccountStatusCode.SERVICE_ERROR);
    }
}
