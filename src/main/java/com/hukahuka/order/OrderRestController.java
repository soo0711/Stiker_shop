package com.hukahuka.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.order.bo.OrderBO;

@RestController
@RequestMapping("/order")
public class OrderRestController {

	@Autowired
	private OrderBO orderBO;
}
