package com.kiss.account.mapper;

import com.kiss.account.entity.Permission;
import com.kiss.account.entity.PermissionModule;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionModuleOutput;
import com.kiss.account.output.PermissionOutput;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    /**
     * 创建权限Mapper
     *
     * @param permission Permission
     */
    void createPermission(Permission permission);

    /**
     * 获取所有权限Mapper
     *
     * @return List<Permission>
     */
    List<PermissionOutput> getPermissions();

    /**
     * 获取所有绑定的权限Mapper
     *
     * @return List<BindPermissionOutput>
     */
    List<BindPermissionOutput> getBindPermissions(Integer status);

    /**
     * 查询权限信息
     * @param id Integer
     *
     * @return PermissionOutput
     */
    PermissionOutput getPermissionsByModuleId(Integer id);

    /**
     * 根据权限名称或者权限码获取权限
     * @param permission
     * @return
     */
    Permission getPermissionByNameOrCode (Permission permission);

    Integer putPermission (PermissionOutput permissionOutput);

    Integer deletePermission (Integer id);

    List<Permission> getPermissionByModuleId (Integer id);
}
