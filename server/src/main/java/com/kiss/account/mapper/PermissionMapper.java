package com.kiss.account.mapper;

import com.kiss.account.entity.Permission;
import com.kiss.account.output.BindPermissionOutput;
import com.kiss.account.output.PermissionOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {

    /**
     * 创建权限
     *
     * @param permission Permission
     */
    void createPermission(Permission permission);

    /**
     * 查询所有权限
     *
     * @return List<Permission>
     */
    List<PermissionOutput> getPermissions();

    /**
     * 查询所有绑定的权限
     *
     * @return List<BindPermissionOutput>
     */
    List<BindPermissionOutput> getBindPermissions(Integer status);

    /**
     * 查询权限信息
     *
     * @param id Integer
     * @return PermissionOutput
     */
    PermissionOutput getPermissionsByModuleId(Integer id);

    /**
     * 根据权限名称或者权限码查询权限
     *
     * @param permission
     * @return
     */
    Permission getPermissionByNameOrCode(Permission permission);

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    Integer updatePermission(Permission permission);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    Integer deletePermissionById(Integer id);

    /**
     * 根据模块id查询权限
     *
     * @param moduleId
     * @return
     */
    List<Permission> getPermissionByModuleId(Integer moduleId);

    /**
     * 根据权限id查询权限信息
     * @param id
     * @return
     */
    Permission getPermissionById(Integer id);

    /**
     * 通过权限ID获取权限
     */
    Permission getPermissionByCode(String code);

    /**
     * 查询有效权限数量
     * @return
     */
    Integer getValidPermissionCount();
}
