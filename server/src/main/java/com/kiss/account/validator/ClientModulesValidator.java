package com.kiss.account.validator;

import com.kiss.account.dao.ClientDao;
import com.kiss.account.dao.PermissionDao;
import com.kiss.account.entity.Client;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.input.UpdateClientModulesInput;
import com.kiss.account.status.AccountStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ClientModulesValidator implements Validator {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UpdateClientModulesInput.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (UpdateClientModulesInput.class.isInstance(target)) {
            UpdateClientModulesInput updateClientModulesInput = (UpdateClientModulesInput) target;
            validateClientId(updateClientModulesInput.getClientId(),errors);
            validateClientModuleId(updateClientModulesInput.getModuleIds(),errors);
        }
    }

    public void validateClientId (Integer id,Errors errors) {

        if (id == null) {
            errors.rejectValue("clientId",String.valueOf(AccountStatusCode.CLIENT_ID_NOT_EMPTY),"客户端id不能为空");
        }

        Client client = clientDao.getClientById(id);

        if (client == null) {
            errors.rejectValue("clientId",String.valueOf(AccountStatusCode.CLIENT_ID_NOT_EXIST),"客户端id不存在");
        }
    }

    public void validateClientModuleId (List<Integer> clientModules,Errors errors) {

        if (clientModules == null || clientModules.size() == 0) {
            errors.rejectValue("moduleIds",String.valueOf(AccountStatusCode.CLIENT_MODULEID_NOT_EMPTY),"模块id不能为空");
            return;
        }

        for (Integer moduleId : clientModules) {
            if (moduleId == null) {
                errors.rejectValue("moduleIds",String.valueOf(AccountStatusCode.CLIENT_MODULEID_NOT_EMPTY),"模块id不能为空");
                return;
            }

            PermissionModule permissionModule = permissionDao.getPermissionModuleById(moduleId);

            if (permissionModule == null) {
                errors.rejectValue("moduleIds",String.valueOf(AccountStatusCode.CLIENT_MODULEID_NOT_EXIST),"模块id不存在");
                return;
            }
        }
    }
}
