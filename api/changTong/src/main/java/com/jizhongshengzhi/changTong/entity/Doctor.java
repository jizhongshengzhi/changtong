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

    @OneToOne(mappedBy = "doctor")
    private User user;      // 登录的用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
