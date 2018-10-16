package com.kiss.account.service;

import com.kiss.account.client.AuthClient;
import com.kiss.account.dao.impl.AccountDaoImpl;
import com.kiss.account.entity.Account;
import com.kiss.account.input.LoginInput;
import com.kiss.account.output.AuthOutput;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.JwtUtil;
import com.kiss.account.utils.ResultOutputUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

@RestController
@Api(tags = "Auth", description = "认证相关接口")
public class AuthServiceImpl implements AuthClient {

    @Autowired
    private AccountDaoImpl accountDao;

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

        //生成token
        AuthOutput authOutput = JwtUtil.getToken(account.getId(), account.getUsername());
        authOutput.setName(account.getName());

        return ResultOutputUtil.success(authOutput);
    }
}
