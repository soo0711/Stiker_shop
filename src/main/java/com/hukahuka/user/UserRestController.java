package com.hukahuka.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.common.EncryptUtils;
import com.hukahuka.user.bo.UserBO;
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
	

	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param phoneNumber
	 * @param email
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
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
		Integer userId = userBO.addUser(loginId, hasedPassword, name, phoneNumber, email);
		
		// userPrivate db insert - salt
		userBO.addUserPrivate(userId, salt);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	

	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		// db select - id가 있는지
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		if (user == null) {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다.");
			return result;
		}
		
		if (password.length() != 10) {
			// user의 salt 가져오기
			Integer userId = userBO.getUserEntityByLoginId(loginId).getId();
			String salt = userBO.getUserPrivateByUserId(userId);
			
			// hasedPassword로 로그인
			password = encryptUtils.SHA256(password, salt);
		}
		
		// db select - id, pw 다 맞는지
		user = userBO.getUserEntityByLoginIdPassword(loginId, password);
		
		if (user != null) {
			// 로그인 세션 
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
			result.put("userName", user.getName());
			result.put("userLoginId", user.getLoginId());
		} else {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다.");
		}
		
		return result;
	}
	
	/**
	 * 아이디 찾기 API
	 * @param name
	 * @param phoneNumber
	 * @param email
	 * @return
	 */
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
	
	
	/**
	 * 비밀번호 찾기 API
	 * @param email
	 * @return
	 */
	// @Transactional
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
	
	/**
	 * 주문화면에서 내 정보 불러오기 API
	 * @param session
	 * @return
	 */
	@PostMapping("/info")
	public Map<String, Object> info(
			HttpSession session){
		String userLoginId = (String) session.getAttribute("userLoginId");
		// db - select
		UserEntity user = userBO.getUserEntityByLoginId(userLoginId);
		Map<String, Object> result = new HashMap<>();
		if (user.getAddress() == null) {
			result.put("code", 300);
			result.put("error_message", "추가하신 주소가 없습니다.");
			return result;
		}
		
		result.put("code", 200);
		result.put("name", user.getName());
		result.put("detail", user.getDetailAddress());
		result.put("address", user.getAddress());
		result.put("postcode", user.getPostcode());
		result.put("start", user.getPhoneNumber().substring(0,3));
		result.put("middle", user.getPhoneNumber().substring(3, 7));
		result.put("end", user.getPhoneNumber().substring(7));
		result.put("email", user.getEmail().split("@")[0]);
		result.put("domain", user.getEmail().split("@")[1]);
		
		result.put("result", "성공");
		
		return result;
	}

	/**
	 * 프로필 수정 API
	 * @param name
	 * @param password
	 * @param phoneNumber
	 * @param email
	 * @param birth
	 * @param address
	 * @param detailAddress
	 * @param postcode
	 * @param refundBank
	 * @param refundAccount
	 * @param session
	 * @return
	 */
	@PostMapping("/update")
	public  Map<String, Object> update(
			@RequestParam("name") String name,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email, 
			@RequestParam(name = "birth", required = false) String birth,
			@RequestParam(name = "address", required = false) String address, 
			@RequestParam(name = "detailAddress", required = false) String detailAddress, 
			@RequestParam(name = "postcode", required = false) Integer postcode,
			@RequestParam(name = "refundBank", required = false) String refundBank,
			@RequestParam(name = "refundAccount", required = false) String refundAccount,
			HttpSession session){
		int userId = (int) session.getAttribute("userId");
		
		
		// 부가적인 정보 바꾸기
		userBO.updateUserEntityPlus(userId, password, birth, postcode, address,
				detailAddress, refundBank, refundAccount);
		// 기본 정보 바꾸기
		userBO.updateUserEntity(userId, name, phoneNumber, email);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	@PostMapping("/delete")
	public  Map<String, Object> delete(
			HttpSession session){
		int userId = (int) session.getAttribute("userId");
		
		
		// db - delete
		userBO.deleteUserEntityById(userId);
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
		
	
}
