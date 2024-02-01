package com.hukahuka.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("sign-up-view")
	public String signUpView(
			Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	@RequestMapping("sign-in-view")
	public String signInView(
			Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
}