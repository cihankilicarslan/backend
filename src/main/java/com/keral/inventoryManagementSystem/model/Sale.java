package com.keral.inventoryManagementSystem.model;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Data
@EqualsAndHashCode(exclude = "product")
@ToString(exclude = "product")
@Table(name = "Sales")
@Entity
public class Sale {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Sale_ID")
	private Long salesId;

	@Column(name = "Quantity_Sold")
	private int quantitySold;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
