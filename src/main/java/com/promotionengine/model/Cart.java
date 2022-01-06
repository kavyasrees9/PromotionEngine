package com.promotionengine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart {

	private List<String> SKUs = new ArrayList<String>();

	private List<Product> nonPromotionalProducts = new ArrayList<Product>();

	private List<PromotionalProductsItem> promotionalProductsItems = new ArrayList<PromotionalProductsItem>();

	public List<String> getSKUs() {
		return SKUs;
	}

	public void addItem(String sku) {
		SKUs.add(sku);
	}

	public List<Product> getNonPromotionalProducts() {
		return this.nonPromotionalProducts;
	}

	public List<PromotionalProductsItem> getPromotionalProductsItems() {
		return promotionalProductsItems;
	}

	public void setSKUs(List<String> sKUs) {
		SKUs = sKUs;
	}

	public void setNonPromotionalProducts(List<Product> nonPromotionalProducts) {
		this.nonPromotionalProducts = nonPromotionalProducts;
	}

	public void setPromotionalProductsItems(List<PromotionalProductsItem> promotionalProductsItems) {
		this.promotionalProductsItems = promotionalProductsItems;
	}

	public Cart(List<String> sKUs) {
		super();
		SKUs = sKUs;
	}

	public Cart(String... sKUs) {
		super();
		for (String sku : sKUs)
			SKUs.add(sku);
	}

	public void addPromotionalProductsItem(PromotionalProductsItem promotionalProductsItem) {
		this.promotionalProductsItems.add(promotionalProductsItem);
	}
}
