package com.mysite.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysite.shop.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
	
	

}
