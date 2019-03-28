package com.example.demo.dao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "demo", uniqueConstraints = {@UniqueConstraint(columnNames = {"orderNo"})})
public class Demo {
    @Id
    @Column(columnDefinition = "varchar(32) not null comment 'id'")
    private String id;

    @Column(columnDefinition = "varchar(32) not null comment '订单号'")
    private String orderNo;

    @Column(columnDefinition = "decimal(10,2) not null comment '金额'")
    private BigDecimal price;

    @Column(columnDefinition = "varchar(200) null comment '地址'")
    private String address;

    @Column(columnDefinition = "bigint(20) not null comment '用户Id'")
    private long userId;

    @Column(columnDefinition = "int(11) not null comment '类型'")
    private int type;

    @Column(columnDefinition = "float not null comment '费率'")
    private float rate;

    @Column(columnDefinition = "time null comment '时间'")
    private LocalTime myTime;

    @Column(columnDefinition = "longtext null comment '内容'")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public LocalTime getMyTime() {
        return myTime;
    }

    public void setMyTime(LocalTime myTime) {
        this.myTime = myTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
