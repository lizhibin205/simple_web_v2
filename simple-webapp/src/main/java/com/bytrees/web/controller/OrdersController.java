package com.bytrees.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.web.entity.OrderGoods;
import com.bytrees.web.entity.Orders;
import com.bytrees.web.repository.OrdersRepository;
import com.bytrees.web.utils.ResponseJson;
import com.bytrees.web.vo.OrderGoodsVO;
import com.bytrees.web.vo.OrdersVO;


@RestController
public class OrdersController {
	@Autowired
	private OrdersRepository ordersRepository;
	
	/**
	 * 通过ID获取订单详情
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/orders/{id}")
    public ResponseEntity<ResponseJson<OrdersVO>> index(@PathVariable Long id) {
		Optional<Orders> order = ordersRepository.findById(id);
		if (!order.isPresent()) {
			return new ResponseEntity<>(new ResponseJson<OrdersVO>(404, "order id=" + id + " not found.", null)
					, HttpStatus.NOT_FOUND);
			
		}
		OrdersVO ordersVO = new OrdersVO();
		BeanUtils.copyProperties(order.get(), ordersVO);
		ordersVO.setOrderGoodsList(changeOrderGoodsVOList(order.get().getOrderGoodsList()));
		return new ResponseEntity<>(new ResponseJson<OrdersVO>(200, "sucess.", ordersVO), HttpStatus.OK);
    }

	/**
	 * 类型转换
	 * @param goodsList
	 * @return
	 */
	private List<OrderGoodsVO> changeOrderGoodsVOList(List<OrderGoods> goodsOrderList) {
		List<OrderGoodsVO> goodsOrderVOList = new ArrayList<OrderGoodsVO>();
		if (!goodsOrderList.isEmpty()) {
			for (OrderGoods orderGoods : goodsOrderList) {
				OrderGoodsVO orderGoodsVO = new OrderGoodsVO();
				BeanUtils.copyProperties(orderGoods, orderGoodsVO);
				goodsOrderVOList.add(orderGoodsVO);
			}
		}
		return goodsOrderVOList;
	}
}
