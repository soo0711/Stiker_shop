package com.hukahuka.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.cart.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer>{

	// input: userId	output: List<CartEntity>
	public List<CartEntity> findByUserId(int userId);
	
	// input: userId	output: CartEntity
	public CartEntity findByUserIdAndProductId(int userId, int productId);
	
	// input: productId, userId	output: CartEntity
	public CartEntity findByProductIdAndUserId(int productId, int userId);
	
}
