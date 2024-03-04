package com.hukahuka.cart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hukahuka.cart.bo.CartBO;
import com.hukahuka.cart.bo.CartServiceBO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartServiceBO cartServiceBO;

	@GetMapping("/cart-list-view")
	public String CartListView(
			Model model,
			HttpSession session) {
		// userId
		int userId = (int) session.getAttribute("userId");
		
		// db select - user의 cart, product
		List<Map<String, Object>> menuList = cartServiceBO.getMenuCardListByCartAndProduct(userId);
		
		model.addAttribute("carts", menuList);
		model.addAttribute("viewName", "cart/cartList");
		
		return "template/layout";
	}
}
