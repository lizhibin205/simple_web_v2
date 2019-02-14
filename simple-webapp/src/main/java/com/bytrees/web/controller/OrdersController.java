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
	 * 创建订单
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/orders/create")
	public ResponseEntity<ResponseJson<String>> create() {
		//模拟订单
		Orders orders = new Orders();
		orders.setOrderNumber("A20190214973");
		orders.setTotalPrice(100.0);

		List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>();
		OrderGoods oGoods = new OrderGoods();
		//这步很关键，不然插入商品记录时，order_id=null导致执行失败
		oGoods.setOrderId(orders);
		oGoods.setGoodsId(5L);
		oGoods.setPrice(50.0);
		oGoods.setNumber(2);
		orderGoodsList.add(oGoods);
		orders.setOrderGoodsList(orderGoodsList);

		//保存
		ordersRepository.save(orders);

		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
	}

	/**
	 * 删除订单
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/orders/{orderId}/delete")
	public ResponseEntity<ResponseJson<String>> delete(@PathVariable Long orderId) {
		Optional<Orders> order = ordersRepository.findById(orderId);
		if (!order.isPresent()) {
			return new ResponseEntity<>(new ResponseJson<String>(500, "order id=" + orderId + " not found.", null)
					, HttpStatus.OK);
		}

		//删除行为
		ordersRepository.deleteById(orderId);

		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
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
