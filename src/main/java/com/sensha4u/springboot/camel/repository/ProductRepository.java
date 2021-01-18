package com.sensha4u.springboot.camel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sensha4u.springboot.camel.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}