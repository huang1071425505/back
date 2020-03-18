package com.linLing.project.dao.sys;

import com.linLing.project.po.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysMenuDao extends JpaRepository<SysMenu, Integer>, JpaSpecificationExecutor<SysMenu> {
    List<SysMenu> findByMenuState(String menuState);

    /**
     * 当前登录人菜单权限
     */
    @Query(value = "SELECT\n" +
            "\tm.* \n" +
            "FROM\n" +
            "\tsys_menu m\n" +
            "\tLEFT JOIN sys_role_permission p ON m.id = p.power_id \n" +
            "\tAND p.power_type = 'menu'\n" +
            "\tLEFT JOIN sys_user_role r ON r.role_id = p.role_id \n" +
            "\tAND r.user_id = ?1 \n" +
            "WHERE\n" +
            "\tr.user_id IS NOT NULL\n" +
            "\tGROUP BY m.id",nativeQuery = true)
    List<SysMenu> menuPermissions(int userId);
}