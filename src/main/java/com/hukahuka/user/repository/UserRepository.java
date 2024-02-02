package com.hukahuka.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// null or UserEntity(단건)
	public UserEntity findByLoginId(String loginId);
	
	// null or UserEntity
	public UserEntity findbyLoginIdAndPassword(String loginId, String password);
}
