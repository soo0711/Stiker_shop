package com.hukahuka.wish;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/{productId}")
	public Map<String, Object> cartList(
			@PathVariable(name = "productId") int  productId,
			HttpSession session){
		int userId = (int) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		// wishToggle
		wishBO.wishToggle(productId, userId);
	
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		return result;

	}
	
}
