package com.linLing.project.dao.xm;

import com.linLing.project.po.XmProjectProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XmProjectProcessDao extends JpaRepository<XmProjectProcess, Integer>, JpaSpecificationExecutor<XmProjectProcess> {
}