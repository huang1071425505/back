package com.linLing.project.dao.sys;

import com.linLing.project.po.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUsersDao extends JpaRepository<SysUsers, Integer>, JpaSpecificationExecutor<SysUsers> {
}