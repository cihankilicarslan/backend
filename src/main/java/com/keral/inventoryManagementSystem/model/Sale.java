package com.keral.inventoryManagementSystem.model;




import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "Sales")
@Entity
public class Sale {

	@Id
	@Column(name = "Sales_ID")
	private String sales_id;

	@Column(name = "Quantity_Sold")
	private String quantitySold;

	@OneToOne
	@JoinColumn(name = "product_id") // This specifies the column name
	private Product product;
}
