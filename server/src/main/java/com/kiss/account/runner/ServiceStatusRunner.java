package com.kiss.account.runner;

import com.kiss.account.dao.ServiceStatusDao;
import com.kiss.account.entity.ServiceStatus;
import com.kiss.account.utils.ServiceStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Order(value = 1)
public class ServiceStatusRunner implements CommandLineRunner {

    @Autowired
    private ServiceStatusDao serviceStatusDao;

    @Override
    public void run(String... args) throws Exception {
        List<ServiceStatus> serviceStatuses = serviceStatusDao.getServiceStatus();
        for (ServiceStatus serviceStatus : serviceStatuses) {
            Map<String,String> map = new HashMap<>();
            if (ServiceStatusUtil.statusMap.containsKey(serviceStatus.getLanguage())) {
                map = ServiceStatusUtil.statusMap.get(serviceStatus.getLanguage());
            }
            map.put(serviceStatus.getStatusName() + serviceStatus.getStatus(),serviceStatus.getValue());
            ServiceStatusUtil.statusMap.put(serviceStatus.getLanguage(),map);
        }
    }
}
