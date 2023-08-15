package com.keral.inventoryManagementSystem.model;


import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "Orders")
@Entity
public class Order {

	@Id
	@Column(name = "Order_ID")
	private String Order_id;

	@OneToOne
	private Supplier supplier;

	@OneToOne
	private Product product;

}
