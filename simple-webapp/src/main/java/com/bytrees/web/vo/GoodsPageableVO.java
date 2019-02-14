package com.bytrees.web.vo;

import java.util.List;

public class GoodsPageableVO {
	private Integer page;
	private Integer pageSize;
	private List<GoodsVO> list;
    public GoodsPageableVO(Integer page, Integer pageSize, List<GoodsVO> list) {
    	this.page = page;
    	this.pageSize = pageSize;
    	this.list = list;
    }
    public Integer getPage() {
    	return page;
    }
    public Integer getPageSize() {
    	return pageSize;
    }
    public List<GoodsVO> getList() {
    	return list;
    }
}
