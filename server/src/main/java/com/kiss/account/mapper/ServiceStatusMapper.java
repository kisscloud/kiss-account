package com.kiss.account.mapper;

import com.kiss.account.entity.ServiceStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceStatusMapper {

    List<ServiceStatus> getServiceStatus();
}
