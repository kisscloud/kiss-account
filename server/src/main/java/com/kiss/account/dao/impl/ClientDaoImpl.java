package com.kiss.account.dao.impl;

import com.kiss.account.dao.ClientDao;
import com.kiss.account.entity.Client;
import com.kiss.account.mapper.ClientMapper;
import com.kiss.account.output.ClientOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientOutput> getClientOutputs() {
        return clientMapper.getClientOutputs();
    }

    @Override
    public ClientOutput getClientOutput(String clientID) {
        return clientMapper.getClientOutput(clientID);
    }

    @Override
    public Client getClient(String clientID) {
        return clientMapper.getClient(clientID);
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
    public Integer deleteClient(String clientID) {
        return clientMapper.deleteClient(clientID);
    }

    @Override
    public Client getClientById(Integer id) {

        return clientMapper.getClientById(id);
    }
}
