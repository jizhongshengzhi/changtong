package com.jizhongshengzhi.changTong.entity;

import com.jizhongshengzhi.changTong.Exception.PhOutOfBoundsException;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Feces
 * @Description: 粪便
 * @Author: chenjie
 **/
@Entity
public class Feces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createDate;    // 产生时间
    private Float ph;           // ph值
    private Byte smell;         // 气味
    private Byte color;         // 颜色

    @ManyToOne
    private OrdinaryUser ordinaryUser;  // 普通用户

    @OneToOne(mappedBy = "feces")
    private HeartRate heartRate;        // 心率

    @OneToMany
    private PhResult phResult;          // ph结果
    @OneToMany
    private SmellResult smellResult;    // 气味结果
    @OneToMany
    private ColorResult colorResult;    // 颜色结果

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        if(ph >= 0 && ph <= 14) {
            this.ph = ph;
        } else {
            throw new PhOutOfBoundsException("ph数据越界");
        }
    }

    public Byte getSmell() {
        return smell;
    }

    public void setSmell(Byte smell) {
        this.smell = smell;
    }

    public Byte getColor() {
        return color;
    }

    public void setColor(Byte color) {
        this.color = color;
    }

    public OrdinaryUser getOrdinaryUser() {
        return ordinaryUser;
    }

    public void setOrdinaryUser(OrdinaryUser ordinaryUser) {
        this.ordinaryUser = ordinaryUser;
    }
}
