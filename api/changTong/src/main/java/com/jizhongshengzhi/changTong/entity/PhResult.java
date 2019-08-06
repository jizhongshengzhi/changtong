package com.jizhongshengzhi.changTong.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName PhResult
 * @Description: ph结果
 * @Author: chenjie
 **/
@Entity
public class PhResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float phValue;  // ph值
    private Integer age;    // 年龄
    private String description; // 描述

    @OneToMany(mappedBy = "fecesList")
    private List<Feces> fecesList;  // 粪便列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPhValue() {
        return phValue;
    }

    public void setPhValue(Float phValue) {
        this.phValue = phValue;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
