package com.hukahuka.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.user.entity.UserEntity;
import com.hukahuka.user.entity.UserPrivateEntity;
import com.hukahuka.user.repository.UserPrivateRepository;
import com.hukahuka.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;

	
	// input: loginId	output: UserEntity
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	// input: params	output: Integer (Id pk)
	public Integer addUser(String loginId, String password,
			String name, String phoneNumber, String email) {
		
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.phoneNumber(phoneNumber)
				.email(email)
				.build()
				);
		
		return userEntity == null? null : userEntity.getId();
	}
	
	// input: loginId, hasedpw		output: UserEntity
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findbyLoginIdAndPassword(loginId, password);
	}
	
}
