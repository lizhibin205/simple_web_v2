package com.bytrees.web.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String orderNumber;
	private Double totalPrice;
    private Timestamp createTime;
    private Timestamp updateTime;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="orderId", fetch=FetchType.EAGER)
    private List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();

    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public String getOrderNumber() {
    	return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
    	this.orderNumber = orderNumber;
    }
    public Double getTotalPrice() {
    	return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
    	this.totalPrice = totalPrice;
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
    public List<OrderGoods> getOrderGoodsList() {
    	return orderGoodsList;
    }
    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
    	this.orderGoodsList = orderGoodsList;
    }
}
