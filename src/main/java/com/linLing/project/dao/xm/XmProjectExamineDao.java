package com.linLing.project.dao.xm;

import com.linLing.project.po.XmProjectExamine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface XmProjectExamineDao extends JpaRepository<XmProjectExamine, Integer>, JpaSpecificationExecutor<XmProjectExamine> {

    /**
     * 验证专家评审是否全部通过
     * @param projectId
     * @return
     */
    @Query(value = "SELECT COUNT(*) from xm_project_examine WHERE project_id=?1 AND examine_state='0'",nativeQuery = true)
    int findOneByProjectIdAndExamineState(int projectId);
}