package com.bytrees.web.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrdersVO {
    private Long id;
	private String orderNumber;
	private Double totalPrice;
    private List<OrderGoodsVO> orderGoodsVOList = new ArrayList<OrderGoodsVO>();

    public List<OrderGoodsVO> getOrderGoodsList() {
    	return orderGoodsVOList;
    }
    public void setOrderGoodsList(List<OrderGoodsVO> orderGoodsVOList) {
    	this.orderGoodsVOList = orderGoodsVOList;
    }
}
