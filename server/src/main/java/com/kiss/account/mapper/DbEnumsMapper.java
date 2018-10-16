package com.kiss.account.mapper;

import com.kiss.account.entity.DbEnums;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DbEnumsMapper {

    /**
     * 查询数据库存放的所有状态码信息
     * @return
     */
    List<DbEnums> getDbEnums();
}
