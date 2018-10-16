package com.kiss.account.mapper;

import com.kiss.account.entity.ServiceStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceStatusMapper {

    /**
     * 查询数据库存放的所有状态码信息
     * @return
     */
    List<ServiceStatus> getServiceStatus();
}
