package com.kiss.account.controller;

import com.kiss.account.client.AuthClient;
import com.kiss.account.entity.Account;
import com.kiss.account.input.LoginInput;
import com.kiss.account.output.AuthOutput;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.LdapUtil;
import exception.StatusException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Auth", description = "认证相关接口")
public class AuthController extends BaseController implements AuthClient {

    @Autowired
    HttpServletRequest request;

    @Override
    @ApiOperation(value = "用户名密码登录")
    public AuthOutput loginWithUsernameAndPassword(@RequestBody LoginInput loginInput) {

        //校验用户名密码
        String username = loginInput.getUsername();
        String password = loginInput.getPassword();
        String clientId = loginInput.getClientId();

        //查询用户信息
        Account account = accountDao.getAccountByUsername(username);

        if (account == null) {
            throw new StatusException(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }

        String salt = account.getSalt();
        String passwordLegal = account.getPassword();

        if (!passwordLegal.equals(LdapUtil.ssha(password, salt))) {
            throw new StatusException(AccountStatusCode.ACCOUNT_PASSWORD_ERROR);
        }

        List<String> permissions = accountDao.getAccountPermissionsByAccountId(account.getId());

        if (!StringUtils.isEmpty(clientId)) {

            if (account.getType() != 1) {
                String authorizationCode = "authorization@" + clientId;
                Boolean authorization = false;

                for (String permissionCode : permissions) {
                    if (authorizationCode.equals(permissionCode)) {
                        authorization = true;
                        break;
                    }
                }

                if (!authorization) {
                    throw new StatusException(AccountStatusCode.CLIENT_AUTHORIZATION_NOT_ENOUGH);
                }
            }

            Map<String, Object> authMap = new HashMap<>();
            authMap.put("authorizationCode", JwtUtil.getToken(account.getId(), clientId, Long.valueOf(authorizationCodeExpired)));
            // @TODO 兼容客户端跳转
            // return authMap);
        }


        Map<String, Object> authMap = JwtUtil.getToken(account.getId(), account.getUsername(),account.getName());
        AuthOutput authOutput = new AuthOutput();
        authOutput.setAccessToken(authMap.get("token").toString());
        authOutput.setExpiredAt(Long.valueOf(authMap.get("expiredAt").toString()));
        authOutput.setUsername(account.getUsername());
        authOutput.setPermissions(permissions);

        return authOutput;
    }
}
