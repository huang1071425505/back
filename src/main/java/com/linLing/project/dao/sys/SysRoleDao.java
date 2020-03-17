package com.linLing.project.dao.sys;

import com.linLing.project.po.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysRoleDao extends JpaRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole> {

    /**
     *验证userCode是否重复
     * @param roleCode
     */
    @Query(value = "SELECT COUNT(*) FROM sys_role where role_code=?1",nativeQuery = true)
    int yzroleCode(String roleCode);

    @Query(value = "SELECT COUNT(*) FROM sys_role where role_code=?1 and role_id!=?2",nativeQuery = true)
    int yzroleCode1(String roleCode,int roleId);

    List<SysRole> findByRoleState(String userState);
}