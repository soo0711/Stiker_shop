package com.hukahuka.order;

import java.util.List;

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

	@PostMapping("/order-view")
	public String orderListView(
		@RequestParam("productId") int productId,
		@RequestParam("count") int count,
		Model model) {
	
		// db select
		MenuCard menuCard = orderBO.getMenuCard(productId);
		
		model.addAttribute("orderCard", menuCard);
		model.addAttribute("count", count);
		model.addAttribute("viewName", "order/orderList");
		
		return "template/layout";
	}
	
	@PostMapping("/order-list-view")
	public String orderView(
		@RequestParam("product") int[] product,
		Model model) {
	
		// db select
		List<MenuCard> menuCard = orderBO.getMenuCardList(product);
		
		model.addAttribute("cartOrderCard", menuCard);
		model.addAttribute("viewName", "order/orderList");
		
		return "template/layout";
	}

}
