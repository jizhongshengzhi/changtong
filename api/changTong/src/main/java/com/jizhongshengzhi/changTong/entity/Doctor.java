package com.jizhongshengzhi.changTong.entity;

import javax.persistence.*;

/**
 * @ClassName Doctor
 * @Description: 医生
 * @Author: chenjie
 **/
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // 姓名

    private String password;    // 密码

    private Byte gender = User.GENDER_default;

    private String hospital;    // 医院

    private String title;       // 职称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
