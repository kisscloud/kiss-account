package com.kiss.account.validator;

import com.kiss.account.dao.ClientDao;
import com.kiss.account.entity.Client;
import com.kiss.account.input.ClientAuthorizationInput;
import com.kiss.account.input.CreateClientInput;
import com.kiss.account.input.GetClientSecretInput;
import com.kiss.account.input.UpdateClientInput;
import com.kiss.account.status.AccountStatusCode;
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
                || clazz.equals(UpdateClientInput.class)
                || clazz.equals(GetClientSecretInput.class)
                || clazz.equals(ClientAuthorizationInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateClientInput.class.isInstance(target)) {
            CreateClientInput clientInput = (CreateClientInput) target;
            validateClientName(clientInput.getClientName(), errors);
            validateClientStatus(clientInput.getStatus(), errors);
        } else if (UpdateClientInput.class.isInstance(target)) {
            UpdateClientInput updateClientInput = (UpdateClientInput) target;
            validateClientId(updateClientInput.getId(), errors);
            validateClientName(updateClientInput.getClientName(), errors);
            validateClientStatus(updateClientInput.getStatus(), errors);
        } else if (GetClientSecretInput.class.isInstance(target)) {
            GetClientSecretInput getClientSecretInput = (GetClientSecretInput) target;
            validateClientId(getClientSecretInput.getId(), errors);
        } else if (ClientAuthorizationInput.class.isInstance(target)) {

        } else {
            errors.rejectValue("data", "", "数据格式错误");
        }
    }

    public void validateClientName(String clientName, Errors errors) {

        if (StringUtils.isEmpty(clientName)) {
            errors.rejectValue("clientName", String.valueOf(AccountStatusCode.CLIENT_NAME_NOT_EMPTY), "客户端名称不能为空");
        }
    }

    public void validateClientStatus(Integer status, Errors errors) {

        if (status != 0 && status != 1) {
            errors.rejectValue("status", String.valueOf(AccountStatusCode.CLIENT_STATUS_NOT_EMPTY), "客户端状态不合法");
        }
    }

    public void validateClientId(Integer id, Errors errors) {

        if (id == null) {
            errors.rejectValue("clientID", String.valueOf(AccountStatusCode.CLIENT_ID_NOT_EMPTY), "客户端id不能为空");
        }

        Client clientOutput = clientDao.getClientById(id);

        if (clientOutput == null) {
            errors.rejectValue("clientID", String.valueOf(AccountStatusCode.CLIENT_ID_NOT_EXIST), "客户端id不存在");
        }
    }
}
