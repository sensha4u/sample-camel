package com.sensha4u.springboot.camel.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discount {

	@Id
	@GeneratedValue //(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer amount;
	
	private boolean active;
	
	private Date discountDate;

	@OneToOne
	private Product product;

	
}