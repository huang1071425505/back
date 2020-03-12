package com.linLing.project.dao.sc;

import com.linLing.project.po.ScPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScPolicyDao extends JpaRepository<ScPolicy, Integer>, JpaSpecificationExecutor<ScPolicy> {
}
