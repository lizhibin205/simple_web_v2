package com.bytrees.web.vo;

public class OrderGoodsVO {
    private Long goodsId;
    private Double price;
    private Integer number;
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
}
