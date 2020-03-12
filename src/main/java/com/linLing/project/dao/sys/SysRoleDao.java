package com.linLing.project.dao.sys;

import com.linLing.project.po.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SysRoleDao extends JpaRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole> {

    /**
     *验证userCode是否重复
     * @param userCode
     */
    @Modifying
    @Query(value = "SELECT COUNT(*) FROM sys_role where user_code=?1",nativeQuery = true)
    int yzroleCode(String userCode);
}