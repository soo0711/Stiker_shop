package com.hukahuka.menu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MenuController {

	@RequestMapping("/home-view")
	public String homeView(
			Model model) {
		
		model.addAttribute("viewName", "menu/home");
		return "template/layout";
	}
}
