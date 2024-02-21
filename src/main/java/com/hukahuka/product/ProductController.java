package com.hukahuka.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.product.bo.ProductServiceBO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceBO productServiceBO;

	@GetMapping("/detail")
	public String detail(
			@RequestParam("productId") int productId,
			HttpSession session,
			Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// db select
		MenuCard menuCard = productServiceBO.generateMenuCardById(productId);
		boolean isWish = true;
		
		if (userId != null) {
			isWish = productServiceBO.isWish(userId, productId);
		} 
		
		model.addAttribute("viewName", "product/detail");
		model.addAttribute("menuCard", menuCard);
		model.addAttribute("isWish", isWish);
		
		return "template/layout";
	}
}
