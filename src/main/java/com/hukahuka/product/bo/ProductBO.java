package com.hukahuka.product.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;
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
	
	// input: String		output: List<Product>
	public List<Product> getProductListByCategory(String category){
		return productMapper.selectProductListByCategory(category);
	}
	
	// input: count		output: X
	public void updateProductByCount(int count, String name) {
		productMapper.updateProductByCount(count, name);
	}
	
	// input: count		output: X
	public void deleteProductManager(int productId) {
		// product 테이블 삭제
		productMapper.deleteProductManagerById(productId);
		
		// product_image 테이블 삭제
		productMapper.deleteProductManagerByProductId(productId);
	}
	
	// input: productId		output: List<Prodcut>
	public List<ProductImage> getProductImageByProductId(int productId){
		return productMapper.selectProductImageByProductId(productId);
	}
	
	// input: int 		output: Product
	public Product getProductById(int productId) {
		return productMapper.selectProductById(productId);
	}
	
	// input: X		output: List<Product>
	public List<Product> getProductListOrderByCreated(){
		return productMapper.selectProductListOrderByCreated();
	}
	
	// input: X		output: List<Product>
	public List<Product> getProductListOrderByBuyCount(){
		return productMapper.selectProductListOrderByBuyCount();
	}
	
	// input: prouductId, count		output: X
	public void updateCount(int[] productId, int[] count) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < productId.length; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("productId", productId[i]);
			map.put("count", count[i]);
			list.add(map);
		}
		productMapper.updateCount(list);
		productMapper.updateBuyCount(list);
	}
	
	// input: productId		output: product
	public Product getProductByProductId(int productId) {
		return productMapper.selectProductById(productId);
	}
}
