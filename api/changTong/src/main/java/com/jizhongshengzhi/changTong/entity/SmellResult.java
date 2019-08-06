package com.jizhongshengzhi.changTong.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName SmellResult
 * @Description: 气味结果
 * @Author: chenjie
 **/
@Entity
public class SmellResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Short smell;    // 气味
    private Short age;      // 年龄
    private String description; // 描述

    @OneToMany(mappedBy = "fecesList")
    private List<Feces> fecesList;  // 粪便列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getSmell() {
        return smell;
    }

    public void setSmell(Short smell) {
        this.smell = smell;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
