package com.kiss.account.dao.impl;

import com.kiss.account.dao.ClientModuleDao;
import com.kiss.account.entity.ClientModule;
import com.kiss.account.mapper.ClientModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientModuleDaoImpl implements ClientModuleDao {

    @Autowired
    private ClientModuleMapper clientModulesMapper;

    @Override
    public Integer createClientModules(List<ClientModule> clientModules) {

        return clientModulesMapper.createClientModules(clientModules);
    }

    @Override
    public Integer deleteClientModulesByClientId(Integer clientId) {

        return clientModulesMapper.deleteClientModulesByClientId(clientId);
    }

    @Override
    public List<ClientModule> getClientModulesByClientId(Integer clientId) {

        return clientModulesMapper.getClientModulesByClientId(clientId);
    }
}
