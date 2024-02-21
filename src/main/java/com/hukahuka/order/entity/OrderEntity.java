package com.hukahuka.order.entity;

import java.util.Date;

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
@Table(name = "order")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="deliveryMessage")
	private String deliveryMessage;
	
	private String name;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	private String email;
	
	private String address;
	
	@Column(name="totalPay")
	private int totalPay;
	
	@Column(name="payMethod")
	private String payMethod;
	
	private String status;
	
	@Column(name="createdAt")
	private Date createdAt;
	
	@Column(name="updatedAt")
	private Date updatedAt;
}
