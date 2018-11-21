package com.kiss.account.dao;

import com.kiss.account.entity.WebHook;

import java.util.List;

public interface WebHookDao {

    Integer createWebHook(WebHook webHook);

    Integer deleteWebHook(Integer id);

    Integer updateWebHook(WebHook webHook);

    List<WebHook> getWebHooks(WebHook webHook);
}
