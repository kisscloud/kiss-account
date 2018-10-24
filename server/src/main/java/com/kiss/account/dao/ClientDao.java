package com.kiss.account.dao;

import com.kiss.account.entity.Client;
import com.kiss.account.output.ClientOutput;

import java.util.List;

public interface ClientDao {

    List<ClientOutput> getClientOutputs();

    ClientOutput getClientOutput(Integer id);

    Client getClient(Integer id);

    Integer createClient(Client client);

    Integer updateClient(Client client);

    Integer deleteClient(Integer id);

    Client getClientById(Integer id);
}
