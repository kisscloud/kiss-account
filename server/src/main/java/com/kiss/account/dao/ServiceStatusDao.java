package com.kiss.account.dao;

import com.kiss.account.entity.ServiceStatus;

import java.util.List;

public interface ServiceStatusDao {

    /**
     * 查询数据库存放的所有状态码信息
     * @return
     */
    List<ServiceStatus> getServiceStatus ();
}
