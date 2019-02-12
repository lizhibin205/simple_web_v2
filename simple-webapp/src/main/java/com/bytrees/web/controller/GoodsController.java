package com.bytrees.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.web.entity.Goods;
import com.bytrees.web.repository.GoodsRepository;
import com.bytrees.web.utils.ResponseJson;
import com.bytrees.web.vo.GoodsVO;

@RestController
public class GoodsController {
	@Autowired
	private GoodsRepository goodsRepository;

	@RequestMapping(method=RequestMethod.GET, value="/goods/{id}")
    public ResponseEntity<ResponseJson<GoodsVO>> index(@PathVariable Long id) {
		Optional<Goods> goods = goodsRepository.findById(id);
		if (!goods.isPresent()) {
			return new ResponseEntity<>(new ResponseJson<GoodsVO>(404, "goods id=" + id + " not found.", null)
					, HttpStatus.NOT_FOUND);
			
		}
		GoodsVO goodsVO = new GoodsVO();
		BeanUtils.copyProperties(goods.get(), goodsVO);
		return new ResponseEntity<>(new ResponseJson<GoodsVO>(200, "sucess.", goodsVO), HttpStatus.OK);
    }

	@RequestMapping(method=RequestMethod.POST, value="/goods/add")
	public ResponseEntity<ResponseJson<String>> add(HttpServletRequest request) {
		String name = request.getParameter("name");
		if (name == null) {
			return new ResponseEntity<>(new ResponseJson<String>(500, "goods name cat not be null.", null), HttpStatus.OK);
		}
		Goods goods = new Goods();
		goods.setName(name);
		goodsRepository.save(goods);
		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/goods/{goodsId}/edit")
	public ResponseEntity<ResponseJson<String>> edit(@PathVariable Long goodsId, HttpServletRequest request) {
		String name = request.getParameter("name");
		if (name == null) {
			return new ResponseEntity<>(new ResponseJson<String>(500, "goods name cat not be null.", null), HttpStatus.OK);
		}
		Optional<Goods> optionalGoods = goodsRepository.findById(goodsId);
		if (!optionalGoods.isPresent()) {
			return new ResponseEntity<>(new ResponseJson<String>(500, "goods id=" + goodsId + " not found.", null)
					, HttpStatus.OK);
			
		}
		Goods goods = optionalGoods.get();
		goods.setName(name);
		goodsRepository.save(goods);
		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/goods/{goodsId}/delete")
	public ResponseEntity<ResponseJson<String>> delete(@PathVariable Long goodsId, HttpServletRequest request) {
		goodsRepository.deleteById(goodsId);
		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
	}
}
