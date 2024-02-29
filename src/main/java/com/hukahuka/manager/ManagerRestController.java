package com.hukahuka.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hukahuka.manager.bo.ManagerBO;
import com.hukahuka.product.domain.Product;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {
	
	@Autowired
	private ManagerBO managerBO;

	@PostMapping("/upload")
	public Map<String, Object> upload(
			@RequestParam("name") String name,
			@RequestParam("count") int count,
			@RequestParam("detail") String detail,
			@RequestParam("introduce") String introduce,
			@RequestParam("category") String category,
			@RequestParam("price") int price,
			@RequestParam("images") List<MultipartFile> files,
			HttpSession session){
		
		String userIoginId = (String)session.getAttribute("userLoginId");
		
		// db insert
		managerBO.addProductManager(userIoginId, name, count, detail, introduce, category, price, files);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam("count") int count,
			@RequestParam("name") String name){
		
		
		// db update
		managerBO.updateProductManager(count, name);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("id") int productId){

		// db delete
		managerBO.deleteProductManager(productId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	
	@PostMapping("/order-update")
	public Map<String, Object> orderUpdate(
			@RequestParam("orderId") int orderId,
			@RequestParam("status") String status){
		
		
		// db update
		managerBO.updateOrdersManager(orderId, status);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	

	// @PostMapping("/order-delete")
	public Map<String, Object> orderDelete(
			@RequestParam("orderId") int orderId){

		// db delete
		managerBO.deleteOrdersManager(orderId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}

