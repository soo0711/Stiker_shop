package com.hukahuka.wish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.wish.entity.WishEntity;

public interface WishRepository extends JpaRepository<WishEntity, Integer>{

	// input: productId, userId 		output: WishEntity
	public WishEntity findByUserIdAndProductId(int userId, int productId);
	
	// input: userId 		output: List<WishEntity>
	public List<WishEntity> findByUserId(int userId);
}
