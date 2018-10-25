package com.kiss.account.mapper;

import com.kiss.account.entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    /**
     * 查询所有客户端信息
     * @return
     */
    List<Client> getClients();

    /**
     * 根据id查询客户端的信息
     * @param id
     * @return
     */
    Client getClientById(Integer id);

    /**
     * 新增客户端
     * @param client
     * @return
     */
    Integer createClient(Client client);

    /**
     * 更新客户端信息
     * @param client
     * @return
     */
    Integer updateClient(Client client);

    /**
     * 删除客户端
     * @param id
     * @return
     */
    Integer deleteClientById(Integer id);

    /**
     * 根据id查询客户端密钥
     * @param id
     * @return
     */
    String getClientSecretById(Integer id);
}
