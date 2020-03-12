package com.linLing.project.dao.xm;

import com.linLing.project.po.XmProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XmProjectDao extends JpaRepository<XmProject, Integer>, JpaSpecificationExecutor<XmProject> {
}
