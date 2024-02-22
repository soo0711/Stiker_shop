package com.hukahuka.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.order.bo.OrderBO;

@RestController
@RequestMapping("/order")
public class OrderRestController {

	@Autowired
	private OrderBO orderBO;

	@PostMapping("/order-list")
	public Map<String, Object> orderList(
			@RequestParam("productId") int productId,
			@RequestParam("count") int count,
			Model model) {
		
		// db select
		MenuCard menuCard = orderBO.getMenuCard(productId);
		
		model.addAttribute("orderCards", menuCard);
		model.addAttribute("count", count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
