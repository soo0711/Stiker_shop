package com.hukahuka.product.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString 
@Data // getters / setters 두개 다 있다.
public class ProductImage {
		private int id;
		private int productId;
		private String imagePath;
		private Date createdAt;
}
