package com.linLing.project.dao.sc;

import com.linLing.project.po.ScCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScCourseDao extends JpaRepository<ScCourse, Integer>, JpaSpecificationExecutor<ScCourse> {
}
