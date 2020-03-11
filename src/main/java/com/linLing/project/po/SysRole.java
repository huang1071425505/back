package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_role", schema = "linling", catalog = "")
public class SysRole {
    private Integer roleId;
    private String roleCode;
    private String roleName;
    private String roleDetails;
    private String roleState;

    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_code", nullable = true, length = 50)
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Basic
    @Column(name = "role_name", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_details", nullable = true, length = 255)
    public String getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(String roleDetails) {
        this.roleDetails = roleDetails;
    }

    @Basic
    @Column(name = "role_state", nullable = true, length = 1)
    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return roleId == sysRole.roleId &&
                Objects.equals(roleCode, sysRole.roleCode) &&
                Objects.equals(roleName, sysRole.roleName) &&
                Objects.equals(roleDetails, sysRole.roleDetails) &&
                Objects.equals(roleState, sysRole.roleState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleCode, roleName, roleDetails, roleState);
    }
}

