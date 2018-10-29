/**
 * @Title:
 * @Date 2018/7/6
 * @Description: TODO
 */
package com.kiss.account.aop;

import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.CodeUtil;
import com.kiss.account.utils.ResultOutputUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import output.ResultOutput;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qianrongli
 * @Title:
 * @Date 2018/10/16
 * @Description: TODO
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @Autowired
    private CodeUtil codeUtil;

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResultOutput exceptionHandle(HttpServletRequest request, Exception e) {

        log.info("{}系统异常,接口：",request.getRequestURI());

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodException = ((MethodArgumentNotValidException) e);
            BindingResult bindingResult = methodException.getBindingResult();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            Map<String, List<String>> formVerifieds = new HashMap<>();
            String language = StringUtils.isEmpty(request.getHeader("X-LANGUAGE")) ? "zh-CN" : request.getHeader("X-LANGUAGE");

            if (fieldErrorList != null) {
                for (FieldError fieldError : fieldErrorList) {
                    String message = fieldError.getDefaultMessage();
                    String field = fieldError.getField();
                    List<String> messageList = new ArrayList<>();

                    if (formVerifieds.containsKey(field)) {
                        messageList = formVerifieds.get(field);
                    }

                    String returnMessage = codeUtil.getMessage(Integer.parseInt(message));
                    messageList.add(returnMessage == null ? message : returnMessage);
                    formVerifieds.put(field, messageList);
                }
            }

            return ResultOutputUtil.error(AccountStatusCode.VALIDATE_ERROR, codeUtil.getMessage(AccountStatusCode.VALIDATE_ERROR), formVerifieds);
        } else {
            e.printStackTrace();
        }

        return ResultOutputUtil.error(AccountStatusCode.SERVICE_ERROR);
    }
}
