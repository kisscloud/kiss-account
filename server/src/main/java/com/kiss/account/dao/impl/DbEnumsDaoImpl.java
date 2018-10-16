package com.kiss.account.dao.impl;

import com.kiss.account.dao.DbEnumsDao;
import com.kiss.account.entity.DbEnums;
import com.kiss.account.mapper.DbEnumsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbEnumsDaoImpl implements DbEnumsDao {

    @Autowired
    private DbEnumsMapper dbEnumsMapper;

    public List<DbEnums> getServiceStatus () {

        return dbEnumsMapper.getDbEnums();
    }

}

