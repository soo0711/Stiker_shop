package com.hukahuka.cart.bo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.cart.entity.CartEntity;
import com.hukahuka.cart.repository.CartRepository;

@Service
public class CartBO {
	
	@Autowired
	private CartRepository cartRepository;

	// input: productId, userId, count		output: X
	public void addCartEntity(int productId, int userId, int count) {
		cartRepository.save(
				CartEntity.builder()
				.productId(productId)
				.userId(userId)
				.count(count)
				.build()
				);
	}
	
	
	// input:userId, productId		output:CartEntity
	public CartEntity getCartEntityByUserIdAndProductId(int userId, int productId) {
		return cartRepository.findByUserIdAndProductId(userId, productId);
	}
	
	// input: userId, productId		output: void
	public void deleteCartEntityByUserIdAndProductId(int userId, int productId) {
		CartEntity cart = cartRepository.findByUserIdAndProductId(userId, productId);
		if (cart != null) {
		    cartRepository.delete(cart);
		}
	}
	
	// input: userId, productId		output: void
	public void updateCartEntityByUserIdAndProductId(int userId, int productId, int count) {
		CartEntity cart = cartRepository.findByUserIdAndProductId(userId, productId);
        cart = cart.toBuilder()
        		.count(count)
                .build();
        cartRepository.save(cart); 
	}
	
	// input: productId		output: CartEntity
	public CartEntity getCartEntityByProductIdAndUserId(int productId, int userId) {
		return cartRepository.findByProductIdAndUserId(productId, userId);
	}
		
	// input: userId	output: CartEntity
	public List<CartEntity> getCartEntityByUserId(int userId) {
		return cartRepository.findByUserId(userId);
	}
}
