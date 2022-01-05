package com.promotionengine.data;

import java.util.List;

import com.promotionengine.model.Promotion;

public interface IPromotionsProvider {
	
	List<Promotion> GetPromotions();

}
