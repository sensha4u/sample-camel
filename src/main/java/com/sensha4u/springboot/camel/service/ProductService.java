package com.sensha4u.springboot.camel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensha4u.springboot.camel.model.Product;
import com.sensha4u.springboot.camel.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository products;

	@Autowired
	public ProductService(ProductRepository products) {
		this.products = products;
	}

	public Product findProduct(Integer id) {
		Optional<Product> product = products.findById(id);
		if (!product.isPresent()) {
			throw new IllegalStateException("Product could not found for given id:" + id);
		}
		return product.get();
	}

	public Iterable<Product> listProducts() {
		return products.findAll();
	}

	public void save(Product product) {
		products.save(product);
	}
}