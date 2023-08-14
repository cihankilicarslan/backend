package com.keral.inventoryManagementSystem.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Data
@EqualsAndHashCode(exclude = "sale")
@ToString(exclude = "sale")
@Table(name = "Products")
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Product_Id")
	private long productId;

	@Column(name = "Product_Name")
	private String productName;

	@Column(name = "Category")
	private String category;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "Product_Price")
	private double price;
}
