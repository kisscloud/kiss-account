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
 * @author qianrongli
 * @Title:
 * @Date 2018/10/16
 * @Description: TODO
 */
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResultOutput exceptionHandle(HttpServletRequest request, Exception e) {

        System.out.println("系统异常,接口：" + request.getRequestURI());
        System.out.println(e.getMessage());
        System.out.println(e.getClass());

        if (e instanceof MethodArgumentNotValidException) {

            MethodArgumentNotValidException methodException = ((MethodArgumentNotValidException) e);
            BindingResult bindingResult = methodException.getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            String message = fieldError.getDefaultMessage();
            Integer code = AccountStatusCode.VALIDATE_ERROR;
            String returnMessage = "";
            try {

                Field field = AccountStatusCode.class.getDeclaredField(message);

                if (field.get(field.getName()) == null) {
                    code = Integer.parseInt(field.get(field.getName()).toString());
                    String language = StringUtils.isEmpty(request.getHeader("X-LANGUAGE")) ? "zh-CN" : request.getHeader("X-LANGUAGE");
                    returnMessage = CodeUtil.getMessage(language, code);
                }

            } catch (NoSuchFieldException e1) {
                returnMessage = message;
            } catch (Exception e2) {
                e2.printStackTrace();
                return ResultOutputUtil.error(AccountStatusCode.REQUEST_PARAMETER_ERROR);
            } finally {

                return ResultOutputUtil.error(code, returnMessage, null);
            }
        } else {
            e.printStackTrace();
        }
        return ResultOutputUtil.error(AccountStatusCode.SERVICE_ERROR);
    }
}
