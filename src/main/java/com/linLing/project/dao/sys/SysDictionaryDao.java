package com.linLing.project.dao.sys;

import com.linLing.project.po.SysDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysDictionaryDao extends JpaRepository<SysDictionary, Integer>, JpaSpecificationExecutor<SysDictionary> {

    List<SysDictionary> findAllByDicState(String dicState);

    /**
     * 通过dicGroup分组查询
     */
    @Query(value = "SELECT * FROM sys_dictionary WHERE dic_group=?1 and dic_pid!=0",nativeQuery = true)
    List<SysDictionary> findAllByDicGroup(String dicGroup);
}
