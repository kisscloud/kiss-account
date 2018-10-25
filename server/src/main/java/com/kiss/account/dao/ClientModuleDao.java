package com.kiss.account.dao;

import com.kiss.account.entity.ClientModule;

import java.util.List;

public interface ClientModuleDao {

    /**
     * 新增客户端模块
     * @param clientModules
     * @return
     */
    Integer createClientModules (List<ClientModule> clientModules);

    /**
     * 删除客户端模块
     * @param clientId
     * @return
     */
    Integer deleteClientModulesByClientId (Integer clientId);

    /**
     * 查询客户端信息
     * @param clientId
     * @return
     */
    List<ClientModule> getClientModulesByClientId(Integer clientId);
}
