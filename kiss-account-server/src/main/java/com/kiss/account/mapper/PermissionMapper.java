package com.kiss.account.mapper;

import com.kiss.account.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {

    /**
     * 创建权限Mapper
     *
     * @param permission Permission
     */
    void createPermission(Permission permission);
}
