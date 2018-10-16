package com.kiss.account.runner;

import com.kiss.account.dao.DbEnumsDao;
import com.kiss.account.entity.DbEnums;
import com.kiss.account.utils.DbEnumsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Order(value = 1)
public class DbEnumsRunner implements CommandLineRunner {

    @Autowired
    private DbEnumsDao dbEnumsDao;

    @Override
    public void run(String... args) throws Exception {
        List<DbEnums> serviceStatuses = dbEnumsDao.getServiceStatus();
        for (DbEnums serviceStatus : serviceStatuses) {
            Map<String,String> map = new HashMap<>();
            if (DbEnumsUtil.dbEnumsDao.containsKey(serviceStatus.getLanguage())) {
                map = DbEnumsUtil.dbEnumsDao.get(serviceStatus.getLanguage());
            }
            map.put(serviceStatus.getKey() + serviceStatus.getOption(),serviceStatus.getValue());
            DbEnumsUtil.dbEnumsDao.put(serviceStatus.getLanguage(),map);
        }
    }
}
