package com.bytrees.web.vo;

import lombok.Data;

@Data
public class OrderGoodsVO {
    private Long goodsId;
    private Double price;
    private Integer number;
}
