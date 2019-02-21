package com.bytrees.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytrees.service.entity.Orders;

public interface OrdersRepository  extends JpaRepository<Orders, Long>{

}
