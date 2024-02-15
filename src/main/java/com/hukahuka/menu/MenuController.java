package com.hukahuka.menu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MenuController {

	@GetMapping("/home-view")
	public String homeView(
			Model model) {
		
		model.addAttribute("viewName", "menu/home");
		return "template/layout";
	}
	
	@GetMapping("/menu-view")
	public String menuView(
			@RequestParam("menu") String menu) {
		
		
		if (menu.equals("all")) {
			// db select
			
			return "menu/all";
		}
		
		if (menu.equals("best")) {
			
			return "menu/best";
		}
		
		if (menu.equals("new")) {
			
			return "menu/new";
		}
		
		if (menu.equals("keyring")) {
			
			return "menu/keyring";
		}
		
		if (menu.equals("stiker")) {
			
			return "menu/stiker";
		}
		
		if (menu.equals("memo")) {
			
			return "menu/memo";
		}
		
		if (menu.equals("acc")) {
			
			return "menu/acc";
		}
		
		return "menu/all";
		
	}
}
