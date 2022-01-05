package com.promotionengine.model;

public class Product {

	private String SKU_ID;
	public String getSKU_ID() {
		return SKU_ID;
	}
	public void setSKU_ID(String sKU_ID) {
		SKU_ID = sKU_ID;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	
	private double Price;
	public Product(String sKU_ID, double price) {
		super();
		SKU_ID = sKU_ID;
		Price = price;
	}
	
}
