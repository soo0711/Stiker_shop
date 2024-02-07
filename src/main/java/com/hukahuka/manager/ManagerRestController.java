package com.hukahuka.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hukahuka.manager.bo.ManagerBO;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {
	
	@Autowired
	private ManagerBO managerBO;

	@PostMapping("/upload")
	public Map<String, Object> upload(
			@RequestParam("name") String name,
			@RequestParam("count") String count,
			@RequestParam("detailProduct") String detailProduct,
			@RequestParam("introduceProduct") String introduceProduct,
			@RequestParam("category") String category,
			@RequestParam("fileName") MultipartFile fileName){
		
		// db insert
		managerBO.addProudct();
		
		// 
		return result;
	}
	
}

