package com.hukahuka.product.domain;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString 
@Data // getters / setters 두개 다 있다.
public class Product {

	private int id;
	
	private String name;
	
	private int count;
	
	private String introduce;
	
	private String detail;
	
	private String category;
	
	private int buyCount;
	
	private int price;
	
	private Date createdAt;
	
	private Date updatedAt;
}
