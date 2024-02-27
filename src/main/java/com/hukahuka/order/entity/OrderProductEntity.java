package com.hukahuka.order.entity;

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


@Entity
@ToString
@Table(name = "order_product")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="orderId")
	private int orderId;
	
	@Column(name="productId")
	private int productId;
	
	@Column(name="count")
	private int count;
	
	@Column(name="createdAt", updatable = false)
	@UpdateTimestamp
	private Date createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private Date updatedAt;
}
