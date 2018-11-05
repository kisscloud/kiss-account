package com.kiss.account.controller;

import com.kiss.account.client.ClientClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.dao.ClientDao;
import com.kiss.account.dao.ClientModuleDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.Client;
import com.kiss.account.input.ClientAuthorizationInput;
import com.kiss.account.input.CreateClientInput;
import com.kiss.account.input.GetClientSecretInput;
import com.kiss.account.input.UpdateClientInput;
import com.kiss.account.output.AuthOutput;
import com.kiss.account.output.ClientOutput;
import com.kiss.account.service.OperationLogService;
import com.kiss.account.entity.OperationTargetType;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.ResultOutputUtil;
import com.kiss.account.utils.StringUtil;
import com.kiss.account.validator.ClientValidator;
import entity.Guest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;
import utils.GuestUtil;
import utils.JwtUtil;
import utils.ThreadLocalUtil;

import java.util.*;

@RestController
@Api(tags = "Client", description = "客户端相关接口")
public class ClientController implements ClientClient {

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private ClientModuleDao clientModuleDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(clientValidator);
    }

    @Override
    @ApiOperation(value = "获取所有客户端信息")
    public ResultOutput getClients() {


        List<Client> clients = clientDao.getClients();
        List<ClientOutput> clientOutputs = new ArrayList<>();

        for (Client client : clients) {
            ClientOutput clientOutput = new ClientOutput();
            BeanUtils.copyProperties(client, clientOutput);
            clientOutputs.add(clientOutput);
        }

        return ResultOutputUtil.success(clientOutputs);
    }

    @Override
    @ApiOperation(value = "获取客户端信息")
    public ResultOutput getClient(@RequestParam("id") Integer id) {

        Client client = clientDao.getClientById(id);
        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client, clientOutput);

        return ResultOutputUtil.success(clientOutput);
    }

    @Override
    @ApiOperation(value = "添加客户端")
    public ResultOutput createClient(@Validated @RequestBody CreateClientInput clientInput) {

        Guest guest = ThreadLocalUtil.getGuest();

        Client client = new Client();
        BeanUtils.copyProperties(clientInput, client);
        client.setOperatorId(guest.getId());
        client.setOperatorName(guest.getName());
        client.setOperatorIp(guest.getIp());
        client.setClientId(StringUtil.randomUUIDString());
        client.setClientSecret(StringUtil.randomUUIDString());
        Integer count = clientDao.createClient(client);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.CREATE_CLIENT_FAILED);
        }

        operationLogService.saveOperationLog(guest, null, client, "id", OperationTargetType.TYPE_CLIENT);
        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client, clientOutput);

        return ResultOutputUtil.success(clientOutput);
    }

    @Override
    @ApiOperation(value = "更新客户端")
    public ResultOutput updateClient(@Validated @RequestBody UpdateClientInput clientInput) {


        Guest guest = ThreadLocalUtil.getGuest();

        Client oldValue = clientDao.getClientById(clientInput.getId());

        Client client = new Client();
        BeanUtils.copyProperties(clientInput, client);
        client.setOperatorId(guest.getId());
        client.setOperatorName(guest.getName());
        client.setOperatorIp(guest.getIp());

        Integer count = clientDao.updateClient(client);
        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.UPDATE_CLIENT_FAILED);
        }

        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client, clientOutput);
        operationLogService.saveOperationLog(guest, oldValue, client, "id", OperationTargetType.TYPE_CLIENT);

        return ResultOutputUtil.success(clientOutput);
    }

    @Override
    @ApiOperation(value = "删除客户端")
    public ResultOutput deleteClient(@RequestParam("id") Integer id) {

        Client client = clientDao.getClientById(id);

        if (client == null) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_IS_NOT_EXIST);
        }

        Guest guest = ThreadLocalUtil.getGuest();
        Client oldValue = clientDao.getClientById(id);

        Integer count = clientDao.deleteClientById(id);

        if (count == 0) {
            return ResultOutputUtil.error(AccountStatusCode.DELETE_CLIENT_FAILED);
        }

        operationLogService.saveOperationLog(guest, oldValue, null, "id", OperationTargetType.TYPE_CLIENT);

        return ResultOutputUtil.success();
    }


    @Override
    @ApiOperation(value = "获取客户端密钥")
    public ResultOutput getClientSecret(@RequestBody GetClientSecretInput getClientSecretInput) {

        Integer guestId = GuestUtil.getGuestId();

        if (guestId == null) {
            return ResultOutputUtil.error(AccountStatusCode.LOGIN_STATUS_INVALID);
        }

        Account account = accountDao.getAccountById(guestId);
        String encryptPassword = CryptoUtil.hmacSHA256(getClientSecretInput.getPassword(), account.getSalt());

        if (!encryptPassword.equals(account.getPassword())) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_PASSWORD_ERROR);
        }

        String clientSecret = clientDao.getClientSecretById(getClientSecretInput.getId());

        return ResultOutputUtil.success(clientSecret);
    }

    @Override
    @ApiOperation(value = "客户端授权")
    public ResultOutput ClientAuthorization(@Validated @RequestBody ClientAuthorizationInput clientAuthorizationInput) {

        String code = clientAuthorizationInput.getCode();
        String clientId = clientAuthorizationInput.getClientId();
        String secret = clientAuthorizationInput.getSecret();
        Long expired = clientAuthorizationInput.getExpired();

        //判断是否过期
        boolean isExpired = JwtUtil.isNotExpired(code);

        if (!isExpired) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_AUTHORIZATION_EXPIRED);
        }

        Integer userId = JwtUtil.getUserId(code);
        String authClientId = JwtUtil.getClientId(code);

        if (!clientId.equals(authClientId)) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_ID_ERROR);
        }

        Account account = accountDao.getAccountById(userId);

        if (account == null) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }

        Client client = clientDao.getClientByClientId(clientId);

        if (client == null) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_IS_NOT_EXIST);
        }

        if (!secret.equals(client.getClientSecret())) {
            return ResultOutputUtil.error(AccountStatusCode.CLIENT_SECRET_ERROR);
        }

        List<String> permissions = accountDao.getAccountPermissionsByAccountId(account.getId());
        List<String> clientPermissions = clientModuleDao.getClientModulePermissionsByClientId(clientId);

        if (account.getType() != 1) {
            clientPermissions.retainAll(permissions);
        }

        //生成token
        String token = JwtUtil.getToken(account.getId(), account.getUsername(),expired * 1000);
        Long expiredAt = new Date(System.currentTimeMillis() + expired * 1000).getTime();

        AuthOutput authOutput = new AuthOutput();
        authOutput.setAccessToken(token);
        authOutput.setExpiredAt(expiredAt);
        authOutput.setUsername(account.getUsername());
        authOutput.setPermissions(clientPermissions);

        return ResultOutputUtil.success(authOutput);
    }
}
