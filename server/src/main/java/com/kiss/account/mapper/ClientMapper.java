package com.kiss.account.mapper;

import com.kiss.account.entity.Client;
import com.kiss.account.output.ClientOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    List<ClientOutput> getClientOutputs();

    ClientOutput getClientOutput(Integer id);

    Client getClient(Integer id);

    Client getClientById(Integer id);

    Integer createClient(Client client);

    Integer updateClient(Client client);

    Integer deleteClient(Integer id);

}
