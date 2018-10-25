package com.kiss.account.dao;

import com.kiss.account.entity.ClientModule;

import java.util.List;

public interface ClientModuleDao {

    Integer createClientModules (List<ClientModule> clientModules);

    Integer deleteClientModules (Integer clientId);

    List<ClientModule> getClientModules(Integer clientId);
}
