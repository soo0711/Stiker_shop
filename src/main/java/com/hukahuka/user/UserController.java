package com.hukahuka.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.mail.bo.MailBO;
import com.hukahuka.mail.domain.UserMail;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/sign-up-view")
	public String signUpView(
			Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
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
