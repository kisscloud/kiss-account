package com.kiss.account.dao.impl;

import com.kiss.account.dao.WebHookDao;
import com.kiss.account.entity.WebHook;
import com.kiss.account.mapper.WebHookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebHookDaoImpl implements WebHookDao {

    @Autowired
    private WebHookMapper webHookMapper;

    @Override
    public Integer createWebHook(WebHook webHook) {

        return webHookMapper.createWebHook(webHook);
    }

    @Override
    public Integer deleteWebHook(Integer id) {

        return webHookMapper.deleteWebHook(id);
    }

    @Override
    public Integer updateWebHook(WebHook webHook) {

        return webHookMapper.updateWebHook(webHook);
    }

    @Override
    public List<WebHook> getWebHooks(WebHook webHook) {

        return webHookMapper.getWebHooks(webHook);
    }
}
