package com.sapient.productCatalogue.dto;

public class SellerDTO {
	//private int id;
	private String productName;
	private String sellerName;
	private int quantity;
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getProductName() {
		return productName;	
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
