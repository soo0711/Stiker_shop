package com.hukahuka.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.user.entity.UserEntity;
import com.hukahuka.user.entity.UserPrivateEntity;

public interface UserPrivateRepository extends JpaRepository<UserPrivateEntity, Integer>{

	// null or userprivateEntity
	public UserPrivateEntity findByUserId(int userId);
}
