package com.hukahuka.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.user.entity.UserPrivateEntity;
import com.hukahuka.user.repository.UserPrivateRepository;

@Service
public class UserPrivateBO {
	
	@Autowired
	private UserPrivateRepository userPrivateRepository;

	// input: userId, salt		output: X
	public void addUserPrivate(int userId, String salt) {
		userPrivateRepository.save(
		UserPrivateEntity.builder()
		.userId(userId)
		.salt(salt)
		.build()
		);
	}
	
	// input: userId	output: String
	public String getUserPrivateEntityByUserId(int userId) {
		return userPrivateRepository.findByUserId(userId).getSalt();
	}
	
}
