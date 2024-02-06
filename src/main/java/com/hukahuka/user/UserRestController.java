package com.hukahuka.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.common.EncryptUtils;
import com.hukahuka.mail.bo.MailBO;
import com.hukahuka.mail.domain.UserMail;
import com.hukahuka.user.bo.UserBO;
import com.hukahuka.user.bo.UserPrivateBO;
import com.hukahuka.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private EncryptUtils encryptUtils;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private UserPrivateBO userPrivateBO;
	

	/**
	 * 아이디 중복확인 API
	 * @param loginId
	 * @return
	 */
	@PostMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		
		// 중복 확인 db select
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("code", 200);
			result.put("is_duplicated", true);
		} else {
			result.put("code", 300);
			result.put("is_duplicated", false);
		}

		return result;
	}
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email) throws NoSuchAlgorithmException {
		
		// 비밀번호 암호화 SHA-256 알고리즘
		// salt(난수) 저장
		String salt = encryptUtils.createSalt(password);
		
		// 해시값 저장
		String hasedPassword = encryptUtils.SHA256(password, salt);
		
		// user db insert
		Integer userId =userBO.addUser(loginId, hasedPassword, name, phoneNumber, email);
		
		// userPrivate db insert - salt
		userPrivateBO.addUserPrivate(userId, salt);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		// user의 salt 가져오기
		Integer userId = userBO.getUserEntityByLoginId(loginId).getId();
		String salt = userPrivateBO.getUserPrivateEntityByUserId(userId);
		
		// hasedPassword로 로그인
		String hasedPassword = encryptUtils.SHA256(password, salt);
		
		// db select
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hasedPassword);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			// 로그인 세션 
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
			result.put("userName", user.getName());
		} else {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다.");
		}
		
		return result;
	}
	
	
	@PostMapping("/find-id")
	public Map<String, Object> findId(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email){
		
		// db select
		UserEntity user = userBO.getUserEntityByNamePhoneNumberEmail(name, phoneNumber, email);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("userLoginId", user.getLoginId());
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "아이디를 찾을 수 없습니다.");
		}
		
		return result;
	}
	
	
	@Transactional
	@PostMapping("/find-pw")
	public Map<String, Object> findPw(
			@RequestParam("email") String email){
		
		// db select 
		UserEntity user = userBO.getUserEntityByEmail(email);
		
		Map<String, Object> result = new HashMap<>();
		if (user == null) {
			result.put("code", 300);
			result.put("error_message", "존재하지 않는 가입자 입니다.");
			return result;
		}
		
		// send mail
		userBO.sendMail(email);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	
}
