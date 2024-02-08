package com.hukahuka.product.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int count;
	
	private String introduce;
	
	private String detail;
	
	private String category;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@Column(name ="buyCount")
	private int buyCount;
	
	@Column(name="createdAt", updatable = false)
	@UpdateTimestamp
	private Date createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private Date updatedAt;
}
