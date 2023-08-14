package com.keral.inventoryManagementSystem.model;

import java.util.List;



import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "Supplier")
@Entity
public class Supplier {
	
	@Id
	@Column(name = "Supplier_ID")
	private String supplier_id;
	@Column(name = "Supplier_Name")
	private String supplier_name;
	//@Column(name = "Items")
	//private List<String> items;	
}
