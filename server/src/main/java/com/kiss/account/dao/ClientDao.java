package com.kiss.account.dao;

import com.kiss.account.entity.Client;
import com.kiss.account.output.ClientOutput;

import java.util.List;

public interface ClientDao {

    List<ClientOutput> getClientOutputs();

    ClientOutput getClientOutput(String clientID);

    Client getClient(String clientID);

    Integer createClient(Client client);

    Integer updateClient(Client client);

    Integer deleteClient(String clientID);

    Client getClientById(Integer id);
}
