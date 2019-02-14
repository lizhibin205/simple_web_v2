package com.bytrees.web.vo;

import java.util.ArrayList;
import java.util.List;

public class OrdersVO {
    private Long id;
	private String orderNumber;
	private Double totalPrice;
    private List<OrderGoodsVO> orderGoodsVOList = new ArrayList<OrderGoodsVO>();

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
    public List<OrderGoodsVO> getOrderGoodsList() {
    	return orderGoodsVOList;
    }
    public void setOrderGoodsList(List<OrderGoodsVO> orderGoodsVOList) {
    	this.orderGoodsVOList = orderGoodsVOList;
    }
}
