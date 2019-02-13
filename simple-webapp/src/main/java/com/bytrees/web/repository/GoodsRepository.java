package com.bytrees.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bytrees.web.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
	List<Goods> findByPriceBetweenOrderByPriceAsc(Double startPrice, Double endPrice);
	Page<Goods> findByPriceBetweenOrderByPriceAsc(Double startPrice, Double endPrice, Pageable pageable);
}
