package com.sensha4u.springboot.camel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sensha4u.springboot.camel.model.Discount;

@Repository
public interface DiscountRepository  extends CrudRepository<Discount, Integer> {

}