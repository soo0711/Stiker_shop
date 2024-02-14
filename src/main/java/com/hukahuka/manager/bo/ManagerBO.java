package com.hukahuka.manager.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hukahuka.common.FileManagerService;
import com.hukahuka.product.bo.ProductBO;

@Service
public class ManagerBO {

	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public void addProductManager(
			String userloginId, String name, int count, String detail, 
			String introduce, String category, List<MultipartFile> fileName) {
		
		// db insert 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("count", count);
		map.put("detail", detail);
		map.put("introduce", introduce);
		map.put("category", category);
		productBO.addProduct(map);
		
		int productId = Integer.parseInt(map.get("id").toString());
		
		// 이미지 저장
		List<String> imagePath = fileManagerService.saveFile(userloginId, fileName);
		// 이미지 insert
		productBO.addProductImage(productId, imagePath);
		
	}
	
	// input: count, name		output: X
	public void updateProductManager(int count, String name) {
		productBO.updateProductByCount(count, name);
	}
	
	// input: name		output: X
	public void deleteProductManager(int productId) {
		productBO.deleteProductManager(productId);
	}
	
	
}
