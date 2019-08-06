package com.jizhongshengzhi.changTong.entity;

import sun.security.provider.SHA;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName ColorResult
 * @Description: 颜色结果
 * @Author: chenjie
 **/
@Entity
public class ColorResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Short Color;    // 颜色
    private String description; // 描述

    @OneToMany(mappedBy = "fecesList")
    private List<Feces> fecesList;  // 粪便列表

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getColor() {
        return Color;
    }

    public void setColor(Short color) {
        Color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
