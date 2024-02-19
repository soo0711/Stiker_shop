package com.hukahuka.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

	@GetMapping("/cart-list-view")
	public String CartListView(
			Model model) {
		model.addAttribute("viewName", "cart/cartList");
		
		return "template/layout";
	}
}
