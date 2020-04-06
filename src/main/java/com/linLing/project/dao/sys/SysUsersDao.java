package com.linLing.project.dao.sys;

import com.linLing.project.po.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SysUsersDao extends JpaRepository<SysUsers, Integer>, JpaSpecificationExecutor<SysUsers>{
    SysUsers findOneByUserCode(String userCode);
    SysUsers findOneByUserId(int userId);
    /**
     *验证userCode是否重复
     * @param userCode
     */
    @Query(value = "SELECT COUNT(*) FROM sys_users where user_code=?1 and user_state!='0'",nativeQuery = true)
    int yzuserCode(String userCode);

    @Query(value = "SELECT COUNT(*) FROM sys_users where user_code=?1 and user_id!=?2 and user_state!='0'",nativeQuery = true)
    int yzuserCode1(String userCode,int userId);

    /**
     * 获取教师list
     */
    @Query(value = "SELECT\n" +
            "\tu.user_phone userPhone,\n" +
            "\tu.user_details userDetails,\n" +
            "\tu.user_id userId,\n" +
            "\tu.user_name userName,\n" +
            "\tu.user_code userCode,\n" +
            "\ts.role_id roleId\n" +
            "FROM\n" +
            "\tsys_users u\n" +
            "\tLEFT JOIN sys_user_role s ON s.user_id = u.user_id \n" +
            "WHERE\n" +
            " u.user_state ='1'" +
            "\t and s.role_id =?1",nativeQuery = true)
    List<Map<String, Object>> findByUserStateAndAndUserRoleId(int userRoleId);

    /**
     * 获取用户详情
     */
    @Query(value = "SELECT\n" +
            "\tu.user_phone userPhone,\n" +
            "\tu.user_details userDetails,\n" +
            "\tu.user_id userId,\n" +
            "\tu.user_name userName,\n" +
            "\tu.user_code userCode,\n" +
            "\ts.role_id roleId\n" +
            "FROM\n" +
            "\tsys_users u\n" +
            "\tLEFT JOIN sys_user_role s ON s.user_id = u.user_id \n" +
            "WHERE\n" +
            "\tu.user_id =?1",nativeQuery = true)
    Map<String, Object> findUserDetails(int userId);
}