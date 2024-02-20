package com.hukahuka.wish.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.wish.entity.WishEntity;
import com.hukahuka.wish.repository.WishRepository;

@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	// input: productId, userId, count		output: X
	public void addWishEntity(int productId, int userId) {
		wishRepository.save(
				WishEntity.builder()
				.productId(productId)
				.userId(userId)
				.build()
				);
	}
	
	// input: productId, userId 		output: WishEntity
	public WishEntity getWishEntityByUserIdAndProductId(int userId, int productId) {
		return wishRepository.findByUserIdAndProductId(userId, productId);
	}
}
