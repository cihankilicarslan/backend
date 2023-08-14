package com.keral.inventoryManagementSystem.model;


import lombok.Data;

import javax.persistence.*;

//first commit cihan test
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
