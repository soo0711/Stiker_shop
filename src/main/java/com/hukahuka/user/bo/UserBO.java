package com.hukahuka.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.mail.bo.MailBO;
import com.hukahuka.mail.domain.UserMail;
import com.hukahuka.orderCard.bo.OrderCardBO;
import com.hukahuka.orderCard.domain.OrderCard;
import com.hukahuka.user.entity.UserEntity;
import com.hukahuka.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailBO mailBO;

	
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
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
	// input: name, phoneNumber, email 		output:UserEntity
	public UserEntity getUserEntityByNamePhoneNumberEmail(String name, String phoneNumber, String email) {
		return userRepository.findByNameAndPhoneNumberAndEmail(name, phoneNumber, email);
	}
	
	// input: email			output:UserEntity
	public UserEntity getUserEntityByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
	// input: userId, password		output: X
	public void updatePassword(int userId, String password) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.password(password)
				.build();
		
		userRepository.save(user); // 데이터 있으면 수정
	}
	
	// input: email		output: X
	public void sendMail(String email) {
		String str = mailBO.getTempPassword();
		UserMail mail = mailBO.createMailAndChangePassword(email, str);
        mailBO.mailSend(mail);
        int userId = getUserEntityByEmail(email).getId();
        updatePassword(userId, str);
	}
	
	// input: params	output: X
	public void addUserAddress(String address, String detailAddress, int postcode, int userId) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.address(address)
				.detailAddress(detailAddress)
				.postcode(postcode)
				.build();
		
		userRepository.save(user); // 데이터 있으면 수정
	}
	
}
