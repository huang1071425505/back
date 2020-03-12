package com.linLing.project.dao.sc;

import com.linLing.project.po.ScNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScNewsDao extends JpaRepository<ScNews, Integer>, JpaSpecificationExecutor<ScNews> {
}
