package com.hukahuka.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hukahuka.aop.TimeTrace;
import com.hukahuka.orderCard.bo.OrderCardBO;
import com.hukahuka.orderCard.domain.OrderCard;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private OrderCardBO orderCardBO;
	
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
	
	@RequestMapping("/sign-out")
	public String signOut(
			HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign-in-view";
	}
	
	@GetMapping("/find-id-view")
	public String findIdView(
			Model model) {
		model.addAttribute("viewName", "user/findId");
		return "template/layout";
	}
	
 
	@GetMapping("/find-pw-view")
	public String findPwView(
			Model model) {
		model.addAttribute("viewName", "user/findPw");
		return "template/layout";
	}
	
	
}
