package com.bytrees.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytrees.web.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
	List<Goods> findByPriceBetween(Double startPrice, Double endPrice);
}
