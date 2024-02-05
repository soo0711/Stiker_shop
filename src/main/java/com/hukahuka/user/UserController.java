package com.hukahuka.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.Session;
import com.oracle.wls.shaded.org.apache.xalan.lib.Redirect;

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
