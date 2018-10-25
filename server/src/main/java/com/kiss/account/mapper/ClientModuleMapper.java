package com.kiss.account.mapper;

import com.kiss.account.entity.ClientModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientModuleMapper {

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
