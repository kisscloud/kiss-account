package com.kiss.account.controller;

import com.kiss.account.client.AuthClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import com.kiss.account.input.LoginInput;
import com.kiss.account.output.AuthOutput;
import com.kiss.account.status.AccountStatusCode;
import com.kiss.account.utils.LdapUtil;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Auth", description = "认证相关接口")
public class AuthController implements AuthClient {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    HttpServletRequest request;

    @Value("${authorization.code.expired}")
    private String authorizationCodeExpired;

    @Override
    @ApiOperation(value = "用户名密码登录")
    public ResultOutput<AuthOutput> loginWithUsernameAndPassword(@RequestBody LoginInput loginInput) {

        //校验用户名密码
        String username = loginInput.getUsername();
        String password = loginInput.getPassword();
        String clientId = loginInput.getClientId();

        //查询用户信息
        Account account = accountDao.getAccountByUsername(username);

        if (account == null) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_NOT_EXIST);
        }

        String salt = account.getSalt();
        String passwordLegal = account.getPassword();

        if (!passwordLegal.equals(LdapUtil.ssha(password, salt))) {
            return ResultOutputUtil.error(AccountStatusCode.ACCOUNT_PASSWORD_ERROR);
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
                    return ResultOutputUtil.error(AccountStatusCode.CLIENT_AUTHORIZATION_NOT_ENOUGH);
                }
            }

            Map<String, Object> authMap = new HashMap<>();
            authMap.put("authorizationCode", JwtUtil.getToken(account.getId(), clientId, Long.valueOf(authorizationCodeExpired)));

            return ResultOutputUtil.success(authMap);
        }

        //生成token
        Map<String, Object> authMap = JwtUtil.getToken(account.getId(), account.getUsername());
        AuthOutput authOutput = new AuthOutput();
        authOutput.setAccessToken(authMap.get("token").toString());
        authOutput.setExpiredAt(Long.valueOf(authMap.get("expiredAt").toString()));
        authOutput.setUsername(account.getUsername());
        authOutput.setPermissions(permissions);
        return ResultOutputUtil.success(authOutput);
    }
}
