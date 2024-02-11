package com.sapient.productCatalogue.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.productCatalogue.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

	List<Product> findByProductName(String name);
	List<Product> findByBrand(String brand);
	List<Product> findByColor(String color);
	List<Product> findByPrice(Double price);
	List<Product> findBySku(String sku);
	List<Product> findBySize(int size);
}
