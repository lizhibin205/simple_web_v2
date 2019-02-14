package com.bytrees.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytrees.web.entity.Orders;

public interface OrdersRepository  extends JpaRepository<Orders, Long>{

}
