package com.hukahuka.product.entity;

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
	
	private int name;
	
	private int count;
	
	private int introduce;
	
	private int detail;
	
	private int category;
	
	@Column(name="imagePath")
	private int imagePath;
	
	@Column(name="createdAt", updatable = false)
	@UpdateTimestamp
	private int createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private int updatedAt;
}
