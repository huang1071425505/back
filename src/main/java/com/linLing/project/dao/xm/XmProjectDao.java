package com.linLing.project.dao.xm;

import com.linLing.project.po.XmProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface XmProjectDao extends JpaRepository<XmProject, Integer>, JpaSpecificationExecutor<XmProject> {

    /**
     *验证projectCode是否重复
     * @param projectCode
     */
    @Query(value = "SELECT COUNT(*) FROM xm_project where project_code=?1 and project_state!='0'",nativeQuery = true)
    int yzProjectCode(String projectCode);

    @Query(value = "SELECT COUNT(*) FROM xm_project where project_code=?1 and id!=?2 and project_state!='0'",nativeQuery = true)
    int yzProjectCode1(String projectCode,int id);

    /**
     *按项目所属领域分类
     */
    @Query(value = "SELECT\n" +
            "\tcount(*) num,\n" +
            "\td.dic_name \n" +
            "FROM\n" +
            "\txm_project p\n" +
            "\tLEFT JOIN sys_dictionary d ON d.id = p.project_field \n" +
            "GROUP BY\n" +
            "\tp.project_field",nativeQuery = true)
    List<Map<String, Object>> findByProjectField();
}
