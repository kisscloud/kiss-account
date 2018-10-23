package com.kiss.account.validator;

import com.kiss.account.dao.ClientDao;
import com.kiss.account.entity.Client;
import com.kiss.account.input.CreateClientInput;
import com.kiss.account.input.UpdateClientInput;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    @Autowired
    private ClientDao clientDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CreateClientInput.class)
                || clazz.equals(UpdateClientInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (CreateClientInput.class.isInstance(target)) {
            CreateClientInput clientInput = (CreateClientInput) target;
            validateClientName(clientInput.getClientName(),errors);
            validateClientStatus(clientInput.getStatus(),errors);
        } else if (UpdateClientInput.class.isInstance(target)) {
            UpdateClientInput updateClientInput = (UpdateClientInput) target;
            validateClientId(updateClientInput.getClientID(),errors);
            validateClientName(updateClientInput.getClientName(),errors);
            validateClientStatus(updateClientInput.getStatus(),errors);
        } else {
            errors.rejectValue("clientName", "", "数据格式错误");
        }
    }

    public void validateClientName (String clientName,Errors errors) {
        if (StringUtils.isEmpty(clientName)) {
            errors.rejectValue("clientName","","客户端名称不能为空");
        }
    }

    public void validateClientStatus (Integer status,Errors errors) {
        if (status != 0 && status != 1) {
            errors.rejectValue("status","","客户端状态不合法");
        }
    }

    public void validateClientId (String clientID,Errors errors) {
        if (StringUtils.isEmpty(clientID)) {
            errors.rejectValue("clientID","","客户端id不能为空");
        }

        Client clientOutput = clientDao.getClient(clientID);
        if (clientOutput == null) {
            errors.rejectValue("clientID","","客户端id不存在");
        }

    }
}
