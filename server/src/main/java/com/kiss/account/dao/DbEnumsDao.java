package com.kiss.account.dao;

import com.kiss.account.entity.DbEnums;

import java.util.List;

public interface DbEnumsDao {

    /**
     * 查询数据库存放的所有状态码信息
     * @return
     */
    List<DbEnums> getServiceStatus ();
}
