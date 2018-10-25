package com.kiss.account.controller;

import com.kiss.account.client.ClientClient;
import com.kiss.account.dao.ClientDao;
import com.kiss.account.entity.Client;
import com.kiss.account.input.CreateClientInput;
import com.kiss.account.input.UpdateClientInput;
import com.kiss.account.output.ClientOutput;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.utils.StringUtil;
import com.kiss.account.validator.ClientValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Client", description = "客户端相关接口")
public class ClientController implements ClientClient {

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private ClientDao clientDao;

    @InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.setValidator(clientValidator);
    }

    @Override
    @ApiOperation(value = "获取所有客户端信息")
    public ResultOutput getClients() {

        List<ClientOutput> clientOutputs = clientDao.getClientOutputs();
        return ResultOutputUtil.success(clientOutputs == null ? new ArrayList<>() : clientOutputs);
    }

    @Override
    @ApiOperation(value = "获取客户端信息")
    public ResultOutput getClient(@RequestParam("id") Integer id) {

        ClientOutput clientOutput = clientDao.getClientOutput(id);
        return ResultOutputUtil.success(clientOutput == null ? "" : clientOutput);
    }

    @Override
    @ApiOperation(value = "添加客户端")
    public ResultOutput createClient(@Validated @RequestBody CreateClientInput clientInput) {

        Client client = new Client();
        BeanUtils.copyProperties(clientInput,client);
        client.setClientID(StringUtil.randomUUIDString());
        client.setClientSecret(StringUtil.randomUUIDString());
        Integer count = clientDao.createClient(client);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.CREATE_CLIENT_FAILED);
        }

        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client,clientOutput);
        return ResultOutputUtil.success(clientOutput);
    }

    @Override
    @ApiOperation(value = "更新客户端")
    public ResultOutput updateClient(@Validated @RequestBody UpdateClientInput clientInput) {

        Client client = new Client();
        BeanUtils.copyProperties(clientInput,client);
        Integer count = clientDao.updateClient(client);

        if (count == 0) {

            return ResultOutputUtil.error(AccountStatusCode.UPDATE_CLIENT_FAILED);
        }

        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client,clientOutput);
        return ResultOutputUtil.success(clientOutput);
    }

    @Override
    @ApiOperation(value = "删除客户端")
    public ResultOutput deleteClient(@RequestParam("id") Integer id) {

        Client clientOutput = clientDao.getClient(id);

        if (clientOutput == null) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_IS_NOT_EXIST);
        }

        Integer count = clientDao.deleteClient(id);

        if (count == 0) {

            return ResultOutputUtil.error(AccountStatusCode.DELETE_CLIENT_FAILED);
        }

        return ResultOutputUtil.success();
    }
}
