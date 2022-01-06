package com.promotionengine.model;

import java.util.HashMap;
import java.util.Map;

public class Promotion {
	private int id;

	public int getId() {
		return id;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public String getTitle() {
		return title;
	}

	public Map<String, Integer> getProductCountRules() {
		return productCountRules;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public Double getPromotionalFixedPrice() {
		return promotionalFixedPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	private String title;
	private Map<String, Integer> productCountRules = new HashMap<String, Integer>();
	private PromotionType promotionType;
	private Double promotionalFixedPrice;
	private Double discount;
	private DiscountType discountType;

	public Promotion(int id, String title, Map<String, Integer> productCountRules, 
			PromotionType promotionType, DiscountType discountType,
			Double promotionalFixedPrice, Double discount) {
		super();
		this.id = id;
		this.title = title;
		this.productCountRules = productCountRules;
		this.promotionType = promotionType;
		this.discountType = discountType;
		this.promotionalFixedPrice = promotionalFixedPrice;
		this.discount = discount;
	}

}
