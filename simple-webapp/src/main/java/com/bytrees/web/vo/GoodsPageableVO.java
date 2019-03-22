package com.bytrees.web.vo;

import java.util.List;

import lombok.Data;

@Data
public class GoodsPageableVO {
	private Integer page;
	private Integer pageSize;
	private List<GoodsVO> list;
    public GoodsPageableVO(Integer page, Integer pageSize, List<GoodsVO> list) {
    	this.page = page;
    	this.pageSize = pageSize;
    	this.list = list;
    }
}
