package com.jizhongshengzhi.changTong.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrdinaryUser
 * @Description: 普通用户
 * @Author: chenjie
 **/
@Entity
public class OrdinaryUser {
    private static final byte GENDER_MALE = 0;
    private static final byte GENDER_FEMALE = 1;
    private static final byte GENDER_default = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Byte gender = GENDER_default;   // 性别

    private Date birthday;                  // 出生日期

    @OneToOne(mappedBy = "ordinaryUser")    // 登录的用户
    private User user;

    @OneToMany(mappedBy = "ordinaryUser")
    private List<Feces> fecesList;          // 粪便

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
