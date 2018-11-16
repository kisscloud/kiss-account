package com.kiss.account.dao;

import com.kiss.account.entity.Account;
import com.kiss.account.entity.Client;
import java.util.List;

public interface ClientDao {

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

    /**
     * 根据客户端id获取客户端信息
     * @param clientId
     * @return
     */
    Client getClientByClientId(String clientId);

    /**
     * 根据客户端id跟账户名模糊查询所有账户
     * @param code
     * @param name
     * @return
     */
    List<Account> getClientAccounts(String code, String name);
}
