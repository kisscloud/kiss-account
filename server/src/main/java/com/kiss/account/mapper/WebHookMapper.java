package com.kiss.account.mapper;

import com.kiss.account.entity.WebHook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebHookMapper {

    Integer createWebHook(WebHook webHook);

    Integer deleteWebHook(Integer id);

    Integer updateWebHook(WebHook webHook);

    List<WebHook> getWebHooks(WebHook webHook);
}
