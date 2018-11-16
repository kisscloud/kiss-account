package com.kiss.account.dao.impl;

import com.kiss.account.dao.ClientDao;
import com.kiss.account.entity.Account;
import com.kiss.account.entity.Client;
import com.kiss.account.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> getClients() {

        return clientMapper.getClients();
    }

    @Override
    public Client getClientById(Integer id) {

        return clientMapper.getClientById(id);
    }

    @Override
    public Integer createClient(Client client) {

        return clientMapper.createClient(client);
    }

    @Override
    public Integer updateClient(Client client) {

        return clientMapper.updateClient(client);
    }

    @Override
    public Integer deleteClientById(Integer id) {

        return clientMapper.deleteClientById(id);
    }

    @Override
    public String getClientSecretById(Integer id) {

        return clientMapper.getClientSecretById(id);
    }

    @Override
    public Client getClientByClientId(String clientId) {

        return clientMapper.getClientByClientId(clientId);
    }

    @Override
    public List<Account> getClientAccounts(String code, String name) {

        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        params.put("name",name);

        return clientMapper.getClientAccounts(params);
    }
}
