package com.bytrees.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.service.entity.Goods;
import com.bytrees.service.repository.GoodsRepository;
import com.bytrees.web.utils.ResponseJson;
import com.bytrees.web.vo.GoodsPageableVO;
import com.bytrees.web.vo.GoodsVO;

@RestController
public class GoodsController {
	@Autowired
	private GoodsRepository goodsRepository;

	@Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

	/**
	 * 通过ID获取商品
	 * 高并发获取商品信息
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/goods/{id}")
    public ResponseEntity<ResponseJson<GoodsVO>> index(@PathVariable Long id) {
		//过滤非法入参
		if (id < 1) {
			return new ResponseEntity<>(new ResponseJson<GoodsVO>(500, "error goods id.", null), HttpStatus.OK);
		}

		//从redis中获取数据
		GoodsVO goodsVORedis = (GoodsVO)redisTemplate.opsForValue().get("bytrees:goods:" + id.toString());
		if (goodsVORedis instanceof GoodsVO) {
			if (goodsVORedis.empty()) {
				return new ResponseEntity<>(new ResponseJson<GoodsVO>(404, "not exists(from cache).", null), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResponseJson<GoodsVO>(200, "sucess(from cache).", goodsVORedis), HttpStatus.OK);
			}
		}

		//双重验证锁
		GoodsVO goodsVO = new GoodsVO();
		synchronized (this) {
			GoodsVO goodsVORedisSync = (GoodsVO)redisTemplate.opsForValue().get("bytrees:goods:" + id.toString());
			if (goodsVORedisSync instanceof GoodsVO) {
				if (goodsVORedisSync.empty()) {
					return new ResponseEntity<>(new ResponseJson<GoodsVO>(404, "not exists(from cache:2).", null), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(new ResponseJson<GoodsVO>(200, "sucess(from cache:2).", goodsVORedisSync), HttpStatus.OK);
				}
			}
			//从数据库中获取数据
			Optional<Goods> goods = goodsRepository.findById(id);
			if (!goods.isPresent()) {
				redisTemplate.opsForValue().set("bytrees:goods:" + id.toString(), goodsVO, 10, TimeUnit.SECONDS);
			} else {
				BeanUtils.copyProperties(goods.get(), goodsVO);
				redisTemplate.opsForValue().set("bytrees:goods:" + id.toString(), goodsVO, 10, TimeUnit.SECONDS);
			}
		}

		if (goodsVO.empty()) {
			return new ResponseEntity<>(new ResponseJson<GoodsVO>(404, "not exists.", null), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(new ResponseJson<GoodsVO>(200, "sucess.", goodsVO), HttpStatus.OK);
		}
    }

	/**
	 * 添加商品
	 * @param request
	 * @return
	 */
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

	/**
	 * 编辑商品
	 * @param goodsId
	 * @param request
	 * @return
	 */
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

	/**
	 * 删除商品
	 * @param goodsId
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/goods/{goodsId}/delete")
	public ResponseEntity<ResponseJson<String>> delete(@PathVariable Long goodsId, HttpServletRequest request) {
		goodsRepository.deleteById(goodsId);
		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
	}

	/**
	 * 商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/goods/list")
	public ResponseEntity<ResponseJson<GoodsPageableVO>> list(HttpServletRequest request) {
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception ex) {
			return new ResponseEntity<>(new ResponseJson<GoodsPageableVO>(500, "page param error.", null), HttpStatus.OK);
		}
		if (page < 1) {
			page = 1;
		}
		Pageable pageable = PageRequest.of(page - 1, 1);
		Page<Goods> goodsPage = goodsRepository.findAll(pageable);
		GoodsPageableVO goodsPageableVO = new GoodsPageableVO(page, 1, changeGoodsListToGoodsVOList(goodsPage.getContent()));
		return new ResponseEntity<>(new ResponseJson<GoodsPageableVO>(200, "sucess.", goodsPageableVO), HttpStatus.OK);
	}

	/**
	 * 按价格范围查询
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/goods/searchByPrice")
	public ResponseEntity<ResponseJson<List<GoodsVO>>> searchByPrice(HttpServletRequest request) {
		//分页获取
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception ex) {
			return new ResponseEntity<>(new ResponseJson<List<GoodsVO>>(500, "page param error.", null), HttpStatus.OK);
		}
		if (page < 1) {
			page = 1;
		}
		//价格范围
		Double startPrice = 0.0,endPrice = 0.0;
		try {
			startPrice = Double.parseDouble(request.getParameter("startPrice"));
			endPrice = Double.parseDouble(request.getParameter("endPrice"));
		} catch (Exception ex) {
			return new ResponseEntity<>(new ResponseJson<List<GoodsVO>>(500, "price param error.", null), HttpStatus.OK);
		}
		if (endPrice <= startPrice) {
			return new ResponseEntity<>(new ResponseJson<List<GoodsVO>>(500, "endPrice less than startPrice.", null), HttpStatus.OK);
		}
		Pageable pageable = PageRequest.of(page - 1, 1);
		Page<Goods> goodsPage = goodsRepository.findByPriceBetweenOrderByPriceAsc(startPrice, endPrice, pageable);
		List<GoodsVO> goodsVOList = changeGoodsListToGoodsVOList(goodsPage.getContent());
		return new ResponseEntity<>(new ResponseJson<List<GoodsVO>>(200, "sucess.", goodsVOList), HttpStatus.OK);
	}

	/**
	 * 类型转换
	 * @param goodsList
	 * @return
	 */
	private List<GoodsVO> changeGoodsListToGoodsVOList(List<Goods> goodsList) {
		List<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
		if (!goodsList.isEmpty()) {
			for (Goods goods : goodsList) {
				GoodsVO goodsVO = new GoodsVO();
				BeanUtils.copyProperties(goods, goodsVO);
				goodsVOList.add(goodsVO);
			}
		}
		return goodsVOList;
	}
}
