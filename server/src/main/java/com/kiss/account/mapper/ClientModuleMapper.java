package com.kiss.account.mapper;

import com.kiss.account.entity.ClientModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientModuleMapper {

    Integer createClientModules (List<ClientModule> clientModules);

    Integer deleteClientModules (Integer clientId);
}
