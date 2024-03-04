package com.hukahuka.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.common.EncryptUtils;
import com.hukahuka.mail.bo.MailBO;
import com.hukahuka.mail.domain.UserMail;
import com.hukahuka.orderCard.bo.OrderCardBO;
import com.hukahuka.orderCard.domain.OrderCard;
import com.hukahuka.user.entity.UserEntity;
import com.hukahuka.user.entity.UserPrivateEntity;
import com.hukahuka.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EncryptUtils encryptUtils;
	
	@Autowired
	private MailBO mailBO;
	
	@Autowired
	private UserPrivateBO userPrivateBO;

	
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
	public void updateUserEntityPlus(int userId, String password, String birth, Integer postcode, String address,
				String detailAddress, String refundBank, String refundAccount) {
		if (password != "") {
			String salt = getUserPrivateByUserId(userId);
			String hasedPassword = encryptUtils.SHA256(password, salt);
			updatePassword(userId, hasedPassword);
		}
		
		if (birth != "") {
			updateUserEntityByBirth(userId, birth);
		}
		
		if (postcode != null) {
			updateUserEntityByAddress(userId, postcode, address, detailAddress);
		}
		
		if (refundAccount != "") {
			updateUserEntityByRefund(userId, refundBank, refundAccount);
		}
	}
	
	// input: userId, password		output: X
	public void updatePassword(int userId, String password) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.password(password)
				.build();
		
		userRepository.save(user); // 데이터 있으면 수정
	}
	
	// input: userId, birth		output: X
	public void updateUserEntityByBirth(int userId, String birth) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.birth(birth)
				.build();
		
		userRepository.save(user); // 데이터 있으면 수정
	}
	
	// input: userId, address		output: X
	public void updateUserEntityByAddress(int userId, int postcode, String address, String detailAddress) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.postcode(postcode)
				.address(address)
				.detailAddress(detailAddress)
				.build();
		
		userRepository.save(user); // 데이터 있으면 수정
	}
	
	// input: userId, refund		output: X
	public void updateUserEntityByRefund(int userId, String refundBank, String refundAccount) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.refundBank(refundBank)
				.refundAccount(refundAccount)
				.build();
		
		userRepository.save(user); // 데이터 있으면 수정
	}
	
	// input: userId, name, phoneNumber, email		output: X
	public void updateUserEntity(int userId, String name, String phoneNumber, String email) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		
		user = user.toBuilder()
				.name(name)
				.phoneNumber(phoneNumber)
				.email(email)
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
	
	// input: userId	output: X
	public UserEntity getUserEntityById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	// input: userId	output: X
	public String getUserPrivateByUserId(int userId) {
		return userPrivateBO.getUserPrivateEntityByUserId(userId);
	}
	
	// input: userId, salt		output: X
	public void addUserPrivate(int userId, String salt) {
		userPrivateBO.addUserPrivate(userId, salt);
	}
	
	// input: userId		output: X
	public void deleteUserEntityById(int userId) {
		UserEntity user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			userRepository.delete(user);
		}
		// 난수 지우기
		userPrivateBO.deleteUserPrivateByUserId(userId);
	}
}
