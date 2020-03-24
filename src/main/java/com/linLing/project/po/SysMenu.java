package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_menu", schema = "linling", catalog = "")
public class SysMenu {
    private Integer id;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Integer menuPid;
    private String menuState;
    private Integer menuOrder;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "menu_url")
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Basic
    @Column(name = "menu_icon")
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Basic
    @Column(name = "menu_pid")
    public Integer getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Integer menuPid) {
        this.menuPid = menuPid;
    }

    @Basic
    @Column(name = "menu_state")
    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenu sysMenu = (SysMenu) o;
        return id == sysMenu.id &&
                Objects.equals(menuName, sysMenu.menuName) &&
                Objects.equals(menuUrl, sysMenu.menuUrl) &&
                Objects.equals(menuIcon, sysMenu.menuIcon) &&
                Objects.equals(menuPid, sysMenu.menuPid) &&
                Objects.equals(menuState, sysMenu.menuState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuName, menuUrl, menuIcon, menuPid, menuState);
    }

    @Basic
    @Column(name = "menu_order")
    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
}
