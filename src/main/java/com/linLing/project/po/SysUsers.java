package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_users", schema = "linling", catalog = "")
public class SysUsers {
    private Integer userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userState;
    private String userPhone;
    private String userDetails;

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_code", nullable = true, length = 50)
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = true, length = 100)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_state", nullable = true, length = 1)
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @Basic
    @Column(name = "user_phone", nullable = true, length = 50)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_details", nullable = true, length = -1)
    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUsers sysUsers = (SysUsers) o;
        return userId == sysUsers.userId &&
                Objects.equals(userCode, sysUsers.userCode) &&
                Objects.equals(userName, sysUsers.userName) &&
                Objects.equals(userPassword, sysUsers.userPassword) &&
                Objects.equals(userState, sysUsers.userState) &&
                Objects.equals(userPhone, sysUsers.userPhone) &&
                Objects.equals(userDetails, sysUsers.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userCode, userName, userPassword, userPhone, userDetails, userState);
    }
}
