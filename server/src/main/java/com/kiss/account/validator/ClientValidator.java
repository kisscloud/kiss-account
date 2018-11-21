package com.kiss.account.validator;

import com.kiss.account.dao.AuthorizationTargetDao;
import com.kiss.account.dao.ClientDao;
import com.kiss.account.dao.WebHookDao;
import com.kiss.account.entity.AuthorizationTarget;
import com.kiss.account.entity.Client;
import com.kiss.account.entity.WebHook;
import com.kiss.account.input.*;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ClientValidator implements Validator {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private AuthorizationTargetDao authorizationTargetDao;

    @Autowired
    private WebHookDao webHookDao;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.equals(CreateClientInput.class)
                || clazz.equals(UpdateClientInput.class)
                || clazz.equals(GetClientSecretInput.class)
                || clazz.equals(ClientAuthorizationInput.class)
                || clazz.equals(ClientAccountInput.class)
                || clazz.equals(AuthorizationTargetInput.class)
                || clazz.equals(CreateWebHookInput.class)
                || clazz.equals(UpdateWebHookInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (CreateClientInput.class.isInstance(target)) {
            CreateClientInput clientInput = (CreateClientInput) target;
            validateClientName(clientInput.getClientName(), errors);
            validateClientStatus(clientInput.getStatus(), errors);
        } else if (UpdateClientInput.class.isInstance(target)) {
            UpdateClientInput updateClientInput = (UpdateClientInput) target;
            validateId(updateClientInput.getId(), errors);
            validateClientName(updateClientInput.getClientName(), errors);
            validateClientStatus(updateClientInput.getStatus(), errors);
        } else if (GetClientSecretInput.class.isInstance(target)) {
            GetClientSecretInput getClientSecretInput = (GetClientSecretInput) target;
            validateId(getClientSecretInput.getId(), errors);
        } else if (ClientAuthorizationInput.class.isInstance(target)) {

        } else if (ClientAccountInput.class.isInstance(target)) {
            ClientAccountInput clientAccountInput = (ClientAccountInput) target;
            validateAccountName(clientAccountInput.getAccountName(),errors);
            validateClientId(clientAccountInput.getClientId(),errors);
        } else if (AuthorizationTargetInput.class.isInstance(target)) {
            AuthorizationTargetInput authorizationTargetInput = (AuthorizationTargetInput) target;
            boolean idVal = validateId(authorizationTargetInput.getClientId(),errors);

            if (idVal) {
                validateIp(authorizationTargetInput.getClientId(),authorizationTargetInput.getIp(),errors);
            }
        } else if (CreateWebHookInput.class.isInstance(target)) {
            CreateWebHookInput createWebHookInput = (CreateWebHookInput) target;
            validateId(createWebHookInput.getClientId(),errors);
            validateUrl(createWebHookInput.getUrl(),errors);
        } else if (UpdateWebHookInput.class.isInstance(target)) {
            UpdateWebHookInput updateWebHookInput = (UpdateWebHookInput) target;
            validateWebhookId(updateWebHookInput.getId(),errors);
            validateUrl(updateWebHookInput.getUrl(),errors);
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

    public boolean validateId(Integer id, Errors errors) {

        if (id == null) {
            errors.rejectValue("clientID", String.valueOf(AccountStatusCode.CLIENT_ID_IS_EMPTY), "客户端id不能为空");
            return false;
        }

        Client clientOutput = clientDao.getClientById(id);

        if (clientOutput == null) {
            errors.rejectValue("clientID", String.valueOf(AccountStatusCode.CLIENT_ID_NOT_EXIST), "客户端id不存在");
            return false;
        }

        return true;
    }

    public void validateAccountName(String accountName,Errors errors) {

        if (StringUtils.isEmpty(accountName)) {
            errors.rejectValue("accountName",String.valueOf(AccountStatusCode.ACCOUNT_NAME_NOT_EMPTY),"账户名不能为空");
        }
    }

    public void validateClientId(String clientId,Errors errors) {

        if (StringUtils.isEmpty(clientId)) {
            errors.rejectValue("clientId",String.valueOf(AccountStatusCode.CLIENT_ID_IS_EMPTY),"客户端id不能为空");
        }

        Client client = clientDao.getClientByClientId(clientId);

        if (client == null) {
            errors.rejectValue("clientId",String.valueOf(AccountStatusCode.CLIENT_IS_NOT_EXIST),"客户端不存在");
        }
    }

    public void  validateIp(Integer clientId,String ip,Errors errors) {

        if (StringUtils.isEmpty(ip)) {
            errors.rejectValue("ip",String.valueOf(AccountStatusCode.CLIENT_AUTHORIZATION_TARGET_IP_IS_EMPTY),"授权对象ip为空");
            return;
        }

        AuthorizationTarget authorizationTarget = authorizationTargetDao.getAuthorizationTargetsByClientIdAndIp(clientId,ip);

        if (authorizationTarget != null) {
            errors.rejectValue("ip",String.valueOf(AccountStatusCode.CLIENT_AUTHORIZATION_TARGET_IP_IS_EXIST),"授权对象ip已存在");
        }
    }

    public void validateUrl(String url,Errors errors) {

        if (StringUtils.isEmpty(url)) {
            errors.rejectValue("url",String.valueOf(AccountStatusCode.CLIENT_WEBHOOK_URL_IS_EMPTY));
        }
    }

    public void validateWebhookId(Integer id,Errors errors) {

        if (id == null) {
            errors.rejectValue("id",String.valueOf(AccountStatusCode.CLIENT_WEBHOOK_ID_IS_EMPTY));
            return;
        }

        WebHook webHook = new WebHook(id);
        List<WebHook> webHooks = webHookDao.getWebHooks(webHook);

        if (webHooks == null || webHooks.size() == 0) {
            errors.rejectValue("id",String.valueOf(AccountStatusCode.CLIENT_WEBHOOK_NOT_EXIST));
        }
    }
}
