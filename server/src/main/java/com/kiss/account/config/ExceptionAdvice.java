package com.kiss.account.config;

import com.kiss.account.utils.LangUtil;
import enums.StatusCodeEnums;
import exception.StatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private LangUtil langUtil;

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public StatusException exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e) {

        if (e instanceof StatusException) {

            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());

            return (StatusException) e;

        } else if (e instanceof MethodArgumentNotValidException) {

            MethodArgumentNotValidException methodException = ((MethodArgumentNotValidException) e);
            BindingResult bindingResult = methodException.getBindingResult();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            Map<String, List<String>> formFields = new HashMap<>();

            for (FieldError fieldError : fieldErrorList) {
                String code = fieldError.getCode();
                String field = fieldError.getField();
                List<String> messageList = new ArrayList<>();

                if (formFields.containsKey(field)) {
                    messageList = formFields.get(field);
                }

                String returnMessage = langUtil.getCodeMessage(Integer.parseInt(code));
                messageList.add(returnMessage == null ? code : returnMessage);
                formFields.put(field, messageList);
            }

            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());

            return new StatusException(StatusCodeEnums.VALIDATE_ERROR, formFields);
        }

        return new StatusException(StatusCodeEnums.SERVICE_ERROR, e.getMessage());
    }
}
