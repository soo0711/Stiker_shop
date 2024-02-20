package com.hukahuka.wish;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.wish.bo.WishBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/wish")
public class WishRestController {

	@Autowired
	private WishBO wishBO;
	
	@PostMapping("/wish-list")
	public Map<String, Object> cartList(
			@RequestParam("productId") int productId,
			HttpSession session){
		int userId = (int) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		// 이미 장바구니에 있는지
		if (wishBO.getWishEntityByUserIdAndProductId(userId, productId) != null) {
			result.put("code", 500);
			result.put("error_message", "이미 위시리스트에 담겨있습니다.");
			return result;
		}
		
		// db insert
		wishBO.addWishEntity(productId, userId);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}
