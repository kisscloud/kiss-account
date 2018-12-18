package com.kiss.account.controller;

import com.kiss.account.client.ClientModuleClient;
import com.kiss.account.dao.ClientModuleDao;
import com.kiss.account.entity.ClientModule;
import com.kiss.account.input.UpdateClientModulesInput;
import com.kiss.account.output.ClientModuleOutput;
import com.kiss.account.service.OperationLogService;
import com.kiss.account.entity.OperationTargetType;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.validator.ClientModulesValidator;
import entity.Guest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;
import utils.ThreadLocalUtil;

import java.util.ArrayList;
import java.util.List;


@RestController
@Api(tags = "ClientModule", description = "客户端模块相关接口")
public class ClientModuleController extends BaseController implements ClientModuleClient {

    @Autowired
    private ClientModulesValidator clientModulesValidator;

    @InitBinder
    public void initBinder (WebDataBinder binder) {

        binder.setValidator(clientModulesValidator);
    }

    @Override
    @ApiOperation(value = "更新客户端模块")
    public ResultOutput updateClientModules(@Validated @RequestBody UpdateClientModulesInput updateClientModulesInput) {

        Guest guest = ThreadLocalUtil.getGuest();
        List<ClientModule> oldClientModules = clientModuleDao.getClientModulesByClientId(updateClientModulesInput.getClientId());
        clientModuleDao.deleteClientModulesByClientId(updateClientModulesInput.getClientId());
        List<Integer> moduleIds = updateClientModulesInput.getModuleIds();

        List<Integer> oldModuleIds = new ArrayList<>();

        if (oldClientModules != null && oldClientModules.size() != 0) {
            for (ClientModule clientModule : oldClientModules) {
                oldModuleIds.add(clientModule.getModuleId());
            }
        }

        ResultOutput resultOutput = addClientModules(moduleIds,updateClientModulesInput.getClientId());

        if (resultOutput.getCode() == 200) {
            UpdateClientModulesInput oldUpdateClientModulesInput = new UpdateClientModulesInput();
            oldUpdateClientModulesInput.setClientId(updateClientModulesInput.getClientId());
            oldUpdateClientModulesInput.setModuleIds(oldModuleIds);
            operationLogService.saveOperationLog(guest,oldUpdateClientModulesInput,updateClientModulesInput,"clientId",OperationTargetType.TYPE_CLIENT_MODULES);
        }

        return resultOutput;
    }

    @Override
    @ApiOperation(value = "获取客户端模块")
    public ResultOutput getClientModules(Integer clientId) {

        List<ClientModule> clientModules = clientModuleDao.getClientModulesByClientId(clientId);
        List<ClientModuleOutput> clientModuleOutputs = new ArrayList<>();

        for (ClientModule clientModule : clientModules) {
            ClientModuleOutput clientModuleOutput = new ClientModuleOutput();
            BeanUtils.copyProperties(clientModule,clientModuleOutput);
            clientModuleOutputs.add(clientModuleOutput);
        }

        return ResultOutputUtil.success(clientModuleOutputs);
    }

    public ResultOutput addClientModules (List<Integer> moduleIds,Integer clientId) {

        List<ClientModule> clientModules = new ArrayList<>();

        for (Integer moduleId : moduleIds) {
            ClientModule clientModule = new ClientModule();
            clientModule.setClientId(clientId);
            clientModule.setModuleId(moduleId);
            clientModules.add(clientModule);
        }

        Integer count = clientModuleDao.createClientModules(clientModules);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.CREATE_CLIENT_MODULE_FAILED);
        }

        List<ClientModuleOutput> clientModuleOutputs = new ArrayList<>();

        for (ClientModule clientModule : clientModules) {
            ClientModuleOutput clientModuleOutput = new ClientModuleOutput();
            BeanUtils.copyProperties(clientModule,clientModuleOutput);
            clientModuleOutputs.add(clientModuleOutput);
        }

        return ResultOutputUtil.success(clientModuleOutputs);
    }
}
