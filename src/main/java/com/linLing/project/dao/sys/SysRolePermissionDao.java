package com.linLing.project.dao.sys;

import com.linLing.project.po.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysRolePermissionDao extends JpaRepository<SysRolePermission, Integer>, JpaSpecificationExecutor<SysRolePermission> {
    List<SysRolePermission> findByPowerIdAndPowerType(int powerId,String powerType);
    /**
     * 删除单菜单角色权限
     */
    @Modifying
    @Query(value = "delete from sys_role_permission where power_id = ?1 and power_type='menu'", nativeQuery = true)
    void deleteByMenuId(int menuId);
}