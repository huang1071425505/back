package com.linLing.project.dao.sys;

import com.linLing.project.po.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserRoleDao extends JpaRepository<SysUserRole, Integer>, JpaSpecificationExecutor<SysUserRole> {
}