package com.bytrees.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.web.entity.Goods;
import com.bytrees.web.repository.GoodsRepository;

@RestController
public class GoodsController {
	@Autowired
	private GoodsRepository goodsRepository;

	@RequestMapping(method=RequestMethod.GET, value="/goods/{id}")
    public String index(@PathVariable Long id) {
		Optional<Goods> goods = goodsRepository.findById(id);
		if (goods.isPresent()) {
			return goods.get().getName();
		} else {
			return "goods id=" + id.toString() + " not exists.";
		}
    }

	@RequestMapping(method= {RequestMethod.GET, RequestMethod.POST}, value="/goods/add")
	public String add(HttpServletRequest request) {
		String name = request.getParameter("name");
		if (name == null) {
			return "name should not be null.";
		}
		Goods goods = new Goods();
		goods.setName(name);
		goods = goodsRepository.save(goods);
		return "ok, id=" + goods.getId();
	}
}
