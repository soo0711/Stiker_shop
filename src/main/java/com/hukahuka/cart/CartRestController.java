package com.hukahuka.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartRestController {

	@PostMapping("/cart-list")
	public Map<String, Object> cartList(){
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
}
