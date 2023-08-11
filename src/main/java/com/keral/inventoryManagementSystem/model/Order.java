package com.keral.inventoryManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
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
