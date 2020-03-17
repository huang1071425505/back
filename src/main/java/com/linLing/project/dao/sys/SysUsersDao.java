package com.linLing.project.dao.sys;

import com.linLing.project.po.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUsersDao extends JpaRepository<SysUsers, Integer>, JpaSpecificationExecutor<SysUsers>{
    SysUsers findOneByUserCode(String userCode);
    SysUsers findOneByUserId(int userId);
    /**
     *验证userCode是否重复
     * @param userCode
     */
    @Query(value = "SELECT COUNT(*) FROM sys_users where user_code=?1",nativeQuery = true)
    int yzuserCode(String userCode);

    @Query(value = "SELECT COUNT(*) FROM sys_users where user_code=?1 and user_id!=?2",nativeQuery = true)
    int yzuserCode1(String userCode,int userId);

    /**
     * 获取教师list
     */
    List<SysUsers> findByUserStateAndAndUserRoleId(String userState,int userRoleId);

}