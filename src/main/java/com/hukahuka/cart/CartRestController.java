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
		
		Map<String, Object> result = new HashMap<>();
		
		// 이미 장바구니에 있는지
		if (cartBO.getCartEntityByUserIdAndProductId(userId, productId) != null) {
			result.put("code", 500);
			result.put("error_message", "이미 장바구니에 담겨있습니다.");
			return result;
		}
		
		// db insert
		cartBO.addCartEntity(productId, userId, count);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("productId") int productId,
			HttpSession session){
		
		int userId = (int) session.getAttribute("userId");
		
		// db delete
		cartBO.deleteCartEntityByUserIdAndProductId(userId, productId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam("productId") int productId,
			@RequestParam("count") int count,
			HttpSession session){
		
		int userId = (int) session.getAttribute("userId");
		
		// db delete
		cartBO.updateCartEntityByUserIdAndProductId(userId, productId, count);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
