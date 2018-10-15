package com.kiss.account.dao;

import com.kiss.account.entity.ServiceStatus;
import com.kiss.account.mapper.ServiceStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceStatusDao {

    @Autowired
    private ServiceStatusMapper serviceStatusMapper;

    public List<ServiceStatus> getServiceStatus () {

        return serviceStatusMapper.getServiceStatus();
    }

}

