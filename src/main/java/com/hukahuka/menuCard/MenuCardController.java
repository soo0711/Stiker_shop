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
	private ProductBO productBO;
	
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
		
		
		if (menu.equals("all")) {
			// db select
			List<MenuCard> menuCard = menuCardBO.generateMenuCardList();
			model.addAttribute("menuCard", menuCard);
			
			model.addAttribute("viewName", "menu/all");
			
			return "template/layout";
		}
		
		if (menu.equals("best")) {
			
			model.addAttribute("viewName", "menu/category");
			
			
			return "template/layout";
		}
		
		if (menu.equals("new")) {
			
			model.addAttribute("viewName", "menu/category");
			
			return "template/layout";
		}
		
		if (menu.equals("keyring")) {
			// db select
			List<MenuCard> menuCard = menuCardBO.generateMenuCardListByCategory("키링");
			model.addAttribute("menuCard", menuCard);
			model.addAttribute("viewName", "menu/category");
			
			return "template/layout";
		}
		
		if (menu.equals("stiker")) {
			// db select
			List<MenuCard> menuCard = menuCardBO.generateMenuCardListByCategory("스티커");
			model.addAttribute("menuCard", menuCard);
			
			model.addAttribute("viewName", "menu/category");
			
			return "template/layout";
		}
		
		if (menu.equals("memo")) {
			// db select
			List<MenuCard> menuCard = menuCardBO.generateMenuCardListByCategory("메모");
			model.addAttribute("menuCard", menuCard);
			
			model.addAttribute("viewName", "menu/category");
			
			return "template/layout";
		}
		
		if (menu.equals("acc")) {
			// db select
			List<MenuCard> menuCard = menuCardBO.generateMenuCardListByCategory("디지털 악세사리");
			model.addAttribute("menuCard", menuCard);
			
			model.addAttribute("viewName", "menu/category");
			
			return "template/layout";
		}
		
		return "menu/all";
		
	}
}
