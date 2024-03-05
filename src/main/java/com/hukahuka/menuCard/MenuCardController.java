package com.hukahuka.menuCard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.product.bo.ProductBO;
import com.hukahuka.product.domain.Product;


@Controller
public class MenuCardController {
	
	@Autowired
	private MenuCardBO menuCardBO;
	
	@GetMapping("/home-view")
	public String homeView(
			Model model) {
		
		model.addAttribute("viewName", "menu/home");
		return "template/layout";
	}
	
	@GetMapping("/menu-view")
	public String menuView(
			@RequestParam("menu") String menu,
			Model model) {
		
		List<MenuCard> menuCards = menuCardBO.getMenuCardList(menu);
		String menuName = menuCardBO.getCallName(menu);
		
		model.addAttribute("menuCard", menuCards);
		model.addAttribute("viewName", menuName);		
		return "template/layout";
		
	}
}
