package com.hukahuka.cart.bo;

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
}
