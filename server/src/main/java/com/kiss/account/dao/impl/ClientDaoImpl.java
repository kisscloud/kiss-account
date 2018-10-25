package com.kiss.account.dao.impl;

import com.kiss.account.dao.ClientDao;
import com.kiss.account.entity.Client;
import com.kiss.account.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
