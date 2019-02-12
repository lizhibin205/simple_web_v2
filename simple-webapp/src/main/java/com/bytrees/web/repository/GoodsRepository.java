package com.bytrees.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.bytrees.web.entity.Goods;

@Component
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
