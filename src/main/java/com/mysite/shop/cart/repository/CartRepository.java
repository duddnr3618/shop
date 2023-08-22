package com.mysite.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.shop.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	
	
}
