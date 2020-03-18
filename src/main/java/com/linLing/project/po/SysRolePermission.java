package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_role_permission", schema = "linling", catalog = "")
@IdClass(SysRolePermissionPK.class)
public class SysRolePermission {
    private Integer roleId;
    private Integer powerId;
    private String powerType;

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "power_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    @Id
    @Column(name = "power_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

}
