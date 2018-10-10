package com.kiss.account.service;

import com.kiss.account.client.LoginClient;
import com.kiss.account.dao.AccountDao;
import com.kiss.account.entity.Account;
import com.kiss.account.input.LoginInput;
import com.kiss.account.output.ResultOutput;
import com.kiss.account.utils.CryptoUtil;
import com.kiss.account.utils.JwtUtil;
import com.kiss.account.utils.ResultOutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginServiceImpl implements LoginClient {

    @Autowired
    private AccountDao accountDao;

    @Override
    public ResultOutput login(LoginInput loginInput) {
        //校验用户名密码
        String username = loginInput.getUsername();
        String password = loginInput.getPassword();
        //查询用户信息
        Account account = accountDao.getAccountByUsername(username);
        if(account == null) {
            return ResultOutputUtil.error(100001,"账户或密码错误");
        }

        String salt = account.getSalt();
        String passwordLegal = account.getPassword();
        if(!passwordLegal.equals(CryptoUtil.hmacSHA256(password, salt))) {
            return ResultOutputUtil.error(100001,"账户或密码错误");
        }

        //生成token
        Map<String,Object> token = JwtUtil.getToken(account.getId(),account.getUsername());

        return ResultOutputUtil.success(token);
    }
}
