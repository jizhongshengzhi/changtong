package com.jizhongshengzhi.changTong.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName User
 * @Description: 用户
 * @Author: chenjie
 **/
@Entity
public class User {
    private static final String defaultPassword = "123456";
    public static final byte GENDER_MALE = 0;
    public static final byte GENDER_FEMALE = 1;
    public static final byte GENDER_default = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;                    // 用户名

    private String password = defaultPassword;  // 原密码

    @Transient                                  // 不存在于数据表
    private String newPassword;                 // 新密码

    private Byte gender = GENDER_default;       // 性别

    private Date birthday;                      // 出生日期

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @OneToMany(mappedBy = "User")
    private List<Feces> fecesList;              // 粪便


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
