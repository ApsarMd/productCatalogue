package com.sapient.productCatalogue.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.productCatalogue.model.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Integer>{
	List<Seller> findBySellerNameAndProductId(String sellerName, int productId);
	List<Seller> findByProductId(int productId);
}
