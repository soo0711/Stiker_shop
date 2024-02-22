package com.hukahuka.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.order.bo.OrderBO;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderBO orderBO;

	@RequestMapping("/order-list-view")
	public String orderListView(
		@RequestParam(name = "productId", required = false) Integer productId,
		@RequestParam(name = "productId", required = false) Integer count,
		Model model) {
	
		if (productId != null) {
			// db select
			MenuCard menuCard = orderBO.getMenuCard(productId);
			
			model.addAttribute("orderCards", menuCard);
			model.addAttribute("count", count);
		}
		
		model.addAttribute("viewName", "order/orderList");
		
		return "template/layout";
	}
	
	
}
