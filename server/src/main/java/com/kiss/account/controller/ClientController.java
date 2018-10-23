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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

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
    public ResultOutput getClients() {

        List<ClientOutput> clientOutputs = clientDao.getClientOutputs();
        return ResultOutputUtil.success(clientOutputs == null ? "" : clientOutputs);
    }

    @Override
    public ResultOutput getClient(@RequestParam("clientID") String clientID) {

        ClientOutput clientOutput = clientDao.getClientOutput(clientID);
        return ResultOutputUtil.success(clientOutput == null ? "" : clientOutput);
    }

    @Override
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
    public ResultOutput deleteClient(@RequestParam("clientID") String clientID) {

        Client clientOutput = clientDao.getClient(clientID);

        if (clientOutput == null) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_IS_NOT_EXIST);
        }

        Integer count = clientDao.deleteClient(clientID);

        if (count == 0) {

            return ResultOutputUtil.error(AccountStatusCode.DELETE_CLIENT_FAILED);
        }

        return ResultOutputUtil.success();
    }
}
