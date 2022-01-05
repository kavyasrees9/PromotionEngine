package com.promotionengine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart {

	private List<String> SKUs = new ArrayList<String>();

	public List<String> getSKUs() {
		return SKUs;
	}
	public void addItem(String sku) {
		SKUs.add(sku);
	}
	public Cart(List<String> sKUs) {
		super();
		SKUs = sKUs;
	}
	public Cart(String... sKUs) {
		this(Arrays.asList(sKUs));
	}	

}
