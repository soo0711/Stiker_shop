package com.hukahuka.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hukahuka.manager.bo.ManagerBO;

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
			@RequestParam("detailProduct") String detail,
			@RequestParam("introduceProduct") String introduce,
			@RequestParam("category") String category,
			@RequestParam("file") MultipartFile fileName,
			HttpSession session){
		
		String userIoginId = (String)session.getAttribute("userLoginId");
		
		// db insert
		managerBO.addProductManager(userIoginId, name, count, detail, introduce, category, fileName);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}

