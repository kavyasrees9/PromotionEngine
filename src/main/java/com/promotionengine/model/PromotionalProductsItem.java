package com.promotionengine.model;

import java.util.ArrayList;
import java.util.List;

public class PromotionalProductsItem {
	
	private Promotion promotion;
	private List<Product> products = new ArrayList<Product>();
	
	public Promotion getPromotion() {
		return promotion;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public PromotionalProductsItem(Promotion promotion, List<Product> products) {
		super();
		this.promotion = promotion;
		this.products = products;
	}	
}
