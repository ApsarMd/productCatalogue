package com.sapient.productCatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.productCatalogue.dto.SellerDTO;
import com.sapient.productCatalogue.exception.ProductNotFoundException;
import com.sapient.productCatalogue.model.Product;
import com.sapient.productCatalogue.model.Seller;
import com.sapient.productCatalogue.repository.ProductRepository;
import com.sapient.productCatalogue.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	SellerRepository sellerRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	public void save(SellerDTO sellerDto) throws ProductNotFoundException {
		List<Product> product = productRepo.findByProductName(sellerDto.getProductName());
		if(product.size()==0)
			throw new ProductNotFoundException("Product not found in DB!!");
		else
		{
			Seller s = new Seller();
	//		s.setId(sellerDto.getId());
			s.setSellerName(sellerDto.getSellerName());
			s.setQuantity(sellerDto.getQuantity());
			s.setProductId(product.get(0).getProductId());
			
			sellerRepo.save(s);
		}
	}
	
	public void delete(int id) {
		sellerRepo.deleteById(id);
	}
}
