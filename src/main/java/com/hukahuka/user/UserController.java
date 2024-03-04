package com.hukahuka.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hukahuka.aop.TimeTrace;
import com.hukahuka.user.bo.UserBO;
import com.hukahuka.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-up-view")
	public String signUpView(
			Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	@TimeTrace
	@GetMapping("/sign-in-view")
	public String signInView(
			Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	/**
	 * 로그아웃 
	 * @param session
	 * @return
	 */
	@GetMapping("/sign-out")
	public String signOut(
			HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign-in-view";
	}
	
	/**
	 * 아이디 찾기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/find-id-view")
	public String findIdView(
			Model model) {
		model.addAttribute("viewName", "user/findId");
		return "template/layout";
	}
	
 
	/**
	 * 비밀번호 찾기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/find-pw-view")
	public String findPwView(
			Model model) {
		model.addAttribute("viewName", "user/findPw");
		return "template/layout";
	}
	
	/**
	 * 프로필 수정 화면
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/update")
	public String myPage(
			HttpSession session,
			Model model) {
		int userId = (int) session.getAttribute("userId");
		
		// db select 
		UserEntity user = userBO.getUserEntityById(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("start", user.getPhoneNumber().substring(0, 3));
		model.addAttribute("middle", user.getPhoneNumber().substring(3, 7));
		model.addAttribute("end", user.getPhoneNumber().substring(7));
		model.addAttribute("viewName", "user/myPage");
		return "template/layout";
	}
}
