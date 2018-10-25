package com.kiss.account.controller;

import com.kiss.account.client.AuthClient;
import com.kiss.account.dao.impl.AccountDaoImpl;
import com.kiss.account.entity.Account;
import com.kiss.account.input.LoginInput;
import com.kiss.account.output.AuthOutput;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Auth", description = "认证相关接口")
public class AuthController implements AuthClient {

    @Autowired
    private AccountDaoImpl accountDao;

    @Autowired
    HttpServletRequest request;

    @Override
    @ApiOperation(value = "用户名密码登录")
    public ResultOutput<AuthOutput> loginWithUsernameAndPassword(@RequestBody LoginInput loginInput) {

        //校验用户名密码
        String username = loginInput.getUsername();
        String password = loginInput.getPassword();
        //查询用户信息
        Account account = accountDao.getAccountByUsername(username);

        if (account == null) {
            return ResultOutputUtil.error(100001);
        }

        String salt = account.getSalt();
        String passwordLegal = account.getPassword();

        if (!passwordLegal.equals(CryptoUtil.hmacSHA256(password, salt))) {
            return ResultOutputUtil.error(100001);
        }

        List<String> permissions = accountDao.getAccountPermissionsByAccountId(account.getId());
        //生成token
        Map<String,Object> authMap = JwtUtil.getToken(account.getId(), account.getUsername());
        AuthOutput authOutput = new AuthOutput();
        authOutput.setAccessToken(authMap.get("token").toString());
        authOutput.setExpiredAt(Long.valueOf(authMap.get("expiredAt").toString()));
        authOutput.setName(account.getName());
        authOutput.setPermissions(permissions);

        return ResultOutputUtil.success(authOutput);
    }
}
