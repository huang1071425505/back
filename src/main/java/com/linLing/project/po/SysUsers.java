package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_users", schema = "linling", catalog = "")
public class SysUsers {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Basic
    @Column(name = "user_code")
    private String userCode;


    @Basic
    @Column(name = "user_name")
    private String userName;


    @Basic
    @Column(name = "user_password")
    private String userPassword;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
