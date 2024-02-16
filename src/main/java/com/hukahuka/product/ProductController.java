package com.hukahuka.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private MenuCardBO menuCardBO;

	@GetMapping("/detail")
	public String detail(
			@RequestParam("productId") int productId,
			Model model) {
		// db select
		MenuCard menuCard = menuCardBO.generateMenuCardById(productId);
		
		model.addAttribute("viewName", "product/detail");
		model.addAttribute("menuCard", menuCard);
		
		return "template/layout";
	}
}
