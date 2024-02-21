package com.hukahuka.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping("/order-list-view")
	public String orderListView(
			Model model) {
		
		model.addAttribute("viewName", "order/orderList");
		return "template/layout";
	}
}
