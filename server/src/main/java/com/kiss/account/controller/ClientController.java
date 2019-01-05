package com.kiss.account.controller;

import com.kiss.account.client.ClientClient;
import com.kiss.account.entity.*;
import com.kiss.account.input.*;
import com.kiss.account.output.*;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.LdapUtil;
import com.kiss.account.utils.StringUtil;
import com.kiss.account.validator.ClientValidator;
import entity.Guest;
import exception.StatusException;
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
import utils.BeanCopyUtil;
import utils.GuestUtil;
import utils.JwtUtil;
import utils.ThreadLocalUtil;

import java.util.*;

@RestController
@Api(tags = "Client", description = "客户端相关接口")
public class ClientController extends BaseController implements ClientClient {

    @Autowired
    private ClientValidator clientValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(clientValidator);
    }

    @Override
    @ApiOperation(value = "获取所有客户端信息")
    public List<ClientOutput> getClients() {


        List<Client> clients = clientDao.getClients();
        List<ClientOutput> clientOutputs = new ArrayList<>();

        for (Client client : clients) {
            ClientOutput clientOutput = new ClientOutput();
            BeanUtils.copyProperties(client, clientOutput);
            clientOutputs.add(clientOutput);
        }

        return clientOutputs;
    }

    @Override
    @ApiOperation(value = "获取客户端信息")
    public ClientOutput getClient(@RequestParam("id") Integer id) {

        Client client = clientDao.getClientById(id);
        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client, clientOutput);

        return clientOutput;
    }

    @Override
    @ApiOperation(value = "添加客户端")
    public ClientOutput createClient(@Validated @RequestBody CreateClientInput clientInput) {

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
            throw new StatusException(AccountStatusCode.CREATE_CLIENT_FAILED);
        }

        operationLogService.saveOperationLog(guest, null, client, "id", OperationTargetType.TYPE_CLIENT);
        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client, clientOutput);

        return clientOutput;
    }

    @Override
    @ApiOperation(value = "更新客户端")
    public ClientOutput updateClient(@Validated @RequestBody UpdateClientInput clientInput) {


        Guest guest = ThreadLocalUtil.getGuest();

        Client oldValue = clientDao.getClientById(clientInput.getId());

        Client client = new Client();
        BeanUtils.copyProperties(clientInput, client);
        client.setOperatorId(guest.getId());
        client.setOperatorName(guest.getName());
        client.setOperatorIp(guest.getIp());

        Integer count = clientDao.updateClient(client);
        if (count == 0) {
            throw new StatusException(AccountStatusCode.UPDATE_CLIENT_FAILED);
        }

        ClientOutput clientOutput = new ClientOutput();
        BeanUtils.copyProperties(client, clientOutput);
        operationLogService.saveOperationLog(guest, oldValue, client, "id", OperationTargetType.TYPE_CLIENT);

        return clientOutput;
    }

    @Override
    @ApiOperation(value = "删除客户端")
    public void deleteClient(@RequestParam("id") Integer id) {

        Client client = clientDao.getClientById(id);

        if (client == null) {
            throw new StatusException(AccountStatusCode.CLIENT_IS_NOT_EXIST);
        }

        Guest guest = ThreadLocalUtil.getGuest();
        Client oldValue = clientDao.getClientById(id);

        Integer count = clientDao.deleteClientById(id);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.DELETE_CLIENT_FAILED);
        }

        operationLogService.saveOperationLog(guest, oldValue, null, "id", OperationTargetType.TYPE_CLIENT);

    }


    @Override
    @ApiOperation(value = "获取客户端密钥")
    public String getClientSecret(@RequestBody GetClientSecretInput getClientSecretInput) {

        Integer guestId = GuestUtil.getGuestId();

        if (guestId == null) {
            throw new StatusException(AccountStatusCode.LOGIN_STATUS_INVALID);
        }

        Account account = accountDao.getAccountById(guestId);
        String encryptPassword = LdapUtil.ssha(getClientSecretInput.getPassword(), account.getSalt());

        if (!encryptPassword.equals(account.getPassword())) {
            throw new StatusException(AccountStatusCode.ACCOUNT_PASSWORD_ERROR);
        }

        String clientSecret = clientDao.getClientSecretById(getClientSecretInput.getId());

        return clientSecret;
    }

    @Override
    @ApiOperation(value = "客户端授权")
    public AuthOutput ClientAuthorization(@Validated @RequestBody ClientAuthorizationInput clientAuthorizationInput) {

        String code = clientAuthorizationInput.getCode();
        String clientId = clientAuthorizationInput.getClientId();
        String secret = clientAuthorizationInput.getSecret();
        Long expired = clientAuthorizationInput.getExpired();

        //判断是否过期
        boolean isExpired = JwtUtil.isNotExpired(code);

        if (!isExpired) {
            throw new StatusException(AccountStatusCode.CLIENT_AUTHORIZATION_EXPIRED);
        }

        Integer userId = JwtUtil.getUserId(code);
        String authClientId = JwtUtil.getClientId(code);

        if (!clientId.equals(authClientId)) {
            throw new StatusException(AccountStatusCode.CLIENT_ID_ERROR);
        }

        Account account = accountDao.getAccountById(userId);

        if (account == null) {
            throw new StatusException(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }

        Client client = clientDao.getClientByClientId(clientId);

        if (client == null) {
            throw new StatusException(AccountStatusCode.CLIENT_IS_NOT_EXIST);
        }

        if (!secret.equals(client.getClientSecret())) {
            throw new StatusException(AccountStatusCode.CLIENT_SECRET_ERROR);
        }

        List<String> permissions = accountDao.getAccountPermissionsByAccountId(account.getId());
        List<String> clientPermissions = clientModuleDao.getClientModulePermissionsByClientId(clientId);

        if (account.getType() != 1) {
            clientPermissions.retainAll(permissions);
        }

        //生成token
        String token = JwtUtil.getToken(account.getId(), account.getUsername(), account.getName(), expired * 1000);
        Long expiredAt = new Date(System.currentTimeMillis() + expired * 1000).getTime();

        AuthOutput authOutput = new AuthOutput();
        authOutput.setAccessToken(token);
        authOutput.setExpiredAt(expiredAt);
        authOutput.setUsername(account.getUsername());
        authOutput.setAccountId(account.getId());
        authOutput.setPermissions(clientPermissions);
        authOutput.setName(account.getName());

        return authOutput;
    }

    @Override
    @ApiOperation(value = "获取客户端相关账户")
    public List<ClientAccountOutput> getClientAccounts(@Validated @RequestBody ClientAccountInput clientAccountInput) {

        String clientId = clientAccountInput.getClientId();
        String accountName = clientAccountInput.getAccountName();
        String code = "authorization@" + clientId;
        String name = accountName + "%";
        List<Account> accounts = clientDao.getClientAccounts(code, name);
        List<ClientAccountOutput> clientAccounts = BeanCopyUtil.copyList(accounts, ClientAccountOutput.class);

        return clientAccounts;
    }

    @Override
    @ApiOperation(value = "客户端授权")
    public AuthorizationTargetOutput createClientAuthorizationTarget(@Validated @RequestBody AuthorizationTargetInput authorizationTargetInput) {

        AuthorizationTarget authorizationTarget = BeanCopyUtil.copy(authorizationTargetInput, AuthorizationTarget.class);
        Integer count = authorizationTargetDao.createAuthorizationTarget(authorizationTarget);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.CREATE_CLIENT_AUTHORIZATION_TARGET_FAILED);
        }

        AuthorizationTargetOutput authorizationTargetOutput = BeanCopyUtil.copy(authorizationTarget, AuthorizationTargetOutput.class, BeanCopyUtil.defaultFieldNames);

        return authorizationTargetOutput;
    }

    @Override
    @ApiOperation(value = "客户端检查")
    public List<AuthorizationTargetOutput> getClientAuthorizationTarget(@RequestParam("clientId") Integer clientId) {

        List<AuthorizationTarget> authorizationTargets = authorizationTargetDao.getAuthorizationTargetsByClientId(clientId);
        return BeanCopyUtil.copyList(authorizationTargets, AuthorizationTargetOutput.class, BeanCopyUtil.defaultFieldNames);
    }

    @Override
    @ApiOperation(value = "创建回调地址")
    public WebHookOutput createWebHook(@Validated @RequestBody CreateWebHookInput webHookInput) {

        WebHook webHook = BeanCopyUtil.copy(webHookInput, WebHook.class);
        Guest guest = ThreadLocalUtil.getGuest();
        webHook.setOperatorId(guest.getId());
        webHook.setOperatorName(guest.getName());
        Integer count = webHookDao.createWebHook(webHook);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.CREATE_CLIENT_WEBHOOK_FAILED);
        }

        operationLogService.saveOperationLog(guest, null, webHook, "id", OperationTargetType.TYPE_webhook);

        return BeanCopyUtil.copy(webHook, WebHookOutput.class, BeanCopyUtil.defaultFieldNames);
    }

    @Override
    @ApiOperation(value = "删除回调地址")
    public void deleteWebHook(@RequestParam("id") Integer id) {

        WebHook webHook = new WebHook(id);
        List<WebHook> olds = webHookDao.getWebHooks(webHook);

        if (olds == null || olds.size() == 0) {
            throw new StatusException(AccountStatusCode.CLIENT_WEBHOOK_NOT_EXIST);
        }

        Integer count = webHookDao.deleteWebHook(id);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.DELETE_CLIENT_WEBHOOK_FAILED);
        }

        operationLogService.saveOperationLog(ThreadLocalUtil.getGuest(), olds.get(0), null, "id", OperationTargetType.TYPE_webhook);
    }

    @Override
    @ApiOperation(value = "更新回调地址")
    public WebHookOutput updateWebHook(@Validated @RequestBody UpdateWebHookInput updateWebHookInput) {

        WebHook webHook = BeanCopyUtil.copy(updateWebHookInput, WebHook.class);
        List<WebHook> olds = webHookDao.getWebHooks(webHook);
        Integer count = webHookDao.updateWebHook(webHook);

        if (count == 0) {
            throw new StatusException(AccountStatusCode.UPDATE_CLIENT_WEBHOOK_FAILED);
        }

        Guest guest = ThreadLocalUtil.getGuest();
        webHook.setOperatorId(guest.getId());
        webHook.setOperatorName(guest.getName());
        operationLogService.saveOperationLog(guest, olds.get(0), webHook, "id", OperationTargetType.TYPE_webhook);
        WebHookOutput webHookOutput = BeanCopyUtil.copy(webHook, WebHookOutput.class, BeanCopyUtil.defaultFieldNames);

        return webHookOutput;
    }

    @Override
    @ApiOperation(value = "获取回调地址")
    public List<WebHookOutput> getWebHooks(@RequestParam("clientId") Integer clientId, Integer id) {

        WebHook webHook = new WebHook();
        webHook.setId(id);
        webHook.setClientId(clientId);
        List<WebHook> webHooks = webHookDao.getWebHooks(webHook);

        return BeanCopyUtil.copyList(webHooks, WebHookOutput.class, BeanCopyUtil.defaultFieldNames);
    }
}
