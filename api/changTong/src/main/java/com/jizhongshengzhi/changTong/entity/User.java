package com.jizhongshengzhi.changTong.entity;

import javax.persistence.*;

/**
 * @ClassName User
 * @Description: 用户
 * @Author: chenjie
 **/
@Entity
public class User {
    private static final String defaultPassword = "123456";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;    // 用户名

    private String password = defaultPassword;  // 原密码

    @Transient     // 不存在于数据表
    private String newPassword;     // 新密码

    @OneToOne
    private OrdinaryUser ordinaryUser;  // 普通用户

    public OrdinaryUser getOrdinaryUser() {
        return ordinaryUser;
    }

    public void setOrdinaryUser(OrdinaryUser ordinaryUser) {
        this.ordinaryUser = ordinaryUser;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @OneToOne
    private Doctor doctor;  // 医生

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
