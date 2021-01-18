package com.sensha4u.springboot.camel.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name = "discounted-products", query = "select product from Product product where product.discounted IS NOT NULL")
public class Product {

	@Id
	@GeneratedValue //(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Integer price;

	private Integer discounted;
	
	private boolean active;
	
	private Date efectivefrom;

	private Date efectiveto;

}