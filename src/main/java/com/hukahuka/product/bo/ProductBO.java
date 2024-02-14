package com.hukahuka.product.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.product.domain.Product;
import com.hukahuka.product.mapper.ProductMapper;

@Service
public class ProductBO {

	@Autowired
	private ProductMapper productMapper;
	
	// input: params	output: Product
	public void addProduct(Map<String, Object> map) {
		 productMapper.insertProduct(map);
	}
	
	// input: productId, imagePath		output: void
	public void addProductImage(int productId, List<String> imagePath) {
		productMapper.insertProductImage(productId, imagePath);
	}
	
	// input: X		output: List<Product>
	public List<Product> getProductList(){
		return productMapper.selectProductList();
	}
	
	// input: count		output: X
	public void updateProductByCount(int count, String name) {
		productMapper.updateProductByCount(count, name);
	}
	
	// input: count		output: X
	public void deleteProductManager(int productId) {
		
		// 이미지 select - List<String>에 imgPath 넣기
		
		// 이미지 삭제
		
		
		// product 테이블 삭제
		productMapper.deleteProductManagerById(productId);
		
		// product_image 테이블 삭제
		productMapper.deleteProductManagerByProductId(productId);
	}
}
