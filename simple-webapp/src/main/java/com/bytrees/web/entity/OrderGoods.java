package com.bytrees.web.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderGoods {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long goodsId;
    private Double price;
    private Integer number;
    private Timestamp createTime;
    private Timestamp updateTime;
    @ManyToOne(cascade=CascadeType.ALL, optional=false, targetEntity=Orders.class)
    @JoinColumn(name="orderId", nullable=false, insertable = false, updatable = false)
    private Orders orderId;

    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public Long getGoodsId() {
    	return goodsId;
    }
    public void setGoodsId(Long goodsId) {
    	this.goodsId = goodsId;
    }
    public Double getPrice() {
    	return price;
    }
    public void setPrice(Double price) {
    	this.price = price;
    }
    public Integer getNumber() {
    	return number;
    }
    public void setNumber(Integer number) {
    	this.number = number;
    }
    public Timestamp getCreateTime() {
    	return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
    	this.createTime = createTime;
    }
    public Timestamp getUpdateTime() {
    	return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
    	this.updateTime = updateTime;
    }
    public Orders getOrderId() {
    	return orderId;
    }
    public void setOrderId(Orders orderId) {
    	this.orderId = orderId;
    }
}
