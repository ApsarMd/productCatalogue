package com.sapient.productCatalogue.controller;

import com.sapient.productCatalogue.model.Product;
import com.sapient.productCatalogue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/product/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED);
	}

	@GetMapping("/product/{sellerName}/{productId}")
	public ResponseEntity<String> getQuantityBySeller(@PathVariable String sellerName, @PathVariable int productId) {
		return new ResponseEntity<>("{\"count\" : " + service.getProductQuantity(sellerName,productId) + "}", HttpStatus.OK);
	}

	@GetMapping("product/inventory/{productId}")
	public ResponseEntity<String> getInventory(@PathVariable Integer productId) {
		return new ResponseEntity<>("{\"count\" : " + service.getInventory(productId) + "}", HttpStatus.OK);
	}
	
	@GetMapping("/product/brand/{brand}")
	public ResponseEntity<List<Product>> getProductByBrand(@PathVariable String brand) {
		return new ResponseEntity<>(service.getProductByBrand(brand), HttpStatus.OK);
	}
	
	@GetMapping("/product/color/{color}")
	public ResponseEntity<List<Product>> getProductByColor(@PathVariable String color) {
		return new ResponseEntity<>(service.getProductByColor(color), HttpStatus.OK);
	}
	
	@GetMapping("/product/size/{size}")
	public ResponseEntity<List<Product>> getProductBySize(@PathVariable Integer size) {
		return new ResponseEntity<>(service.getProductBySize(size), HttpStatus.OK);
	}
	
	@GetMapping("/product/price/{price}")
	public ResponseEntity<List<Product>> getProductByPrice(@PathVariable Double price) {
		return new ResponseEntity<>(service.getProductByPrice(price), HttpStatus.OK);
	}
	
	@GetMapping("/product/sku/{sku}")
	public ResponseEntity<List<Product>> getProductBySku(@PathVariable String sku) {
		return new ResponseEntity<>(service.getProductBySku(sku), HttpStatus.OK);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		service.deleteProduct(id);
		return new ResponseEntity<>("{\"result\": \"Product Deleted Successfully\"}", HttpStatus.OK);
	}
}