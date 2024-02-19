package com.hukahuka.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.cart.bo.CartBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	@Autowired
	private CartBO cartBO;
	
	@PostMapping("/cart-list")
	public Map<String, Object> cartList(
			@RequestParam("productId") int productId,
			@RequestParam("buyCount") int count,
			HttpSession session){
		int userId = (int) session.getAttribute("userId");
		
		// db insert
		cartBO.addCartEntity(productId, userId, count);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
