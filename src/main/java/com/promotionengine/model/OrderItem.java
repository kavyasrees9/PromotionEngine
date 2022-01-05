package com.promotionengine.model;

public class OrderItem {
	private Product Product;
	private long Quantity;
	public Product getProduct() {
		return Product;
	}
	public void setProduct(Product product) {
		Product = product;
	}
	public long getQuantity() {
		return Quantity;
	}
	public void setQuantity(long quantity) {
		Quantity = quantity;
	}
	public OrderItem(Product product, long quantity) {
		super();
		Product = product;
		Quantity = quantity;
	}
}
