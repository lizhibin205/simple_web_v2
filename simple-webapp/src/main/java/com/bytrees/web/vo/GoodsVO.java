package com.bytrees.web.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class GoodsVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
    private String name;
    private Double price;
}
