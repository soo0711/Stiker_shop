package com.hukahuka.wish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.wish.entity.WishEntity;

public interface WishRepository extends JpaRepository<WishEntity, Integer>{

	// input: productId, userId 		output: WishEntity
	public WishEntity findByUserIdAndProductId(int userId, int productId);
}
