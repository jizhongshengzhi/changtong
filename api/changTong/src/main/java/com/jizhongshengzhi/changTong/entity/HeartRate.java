package com.jizhongshengzhi.changTong.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName HeartRate
 * @Description: 心率
 * @Author: chenjie
 **/
@Entity
public class HeartRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Short maxHr; // 最大心率

    private Short minHr; // 最小心率

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getMaxHr() {
        return maxHr;
    }

    public void setMaxHr(Short maxHr) {
        this.maxHr = maxHr;
    }

    public Short getMinHr() {
        return minHr;
    }

    public void setMinHr(Short minHr) {
        this.minHr = minHr;
    }

}
