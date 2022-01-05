package com.promotionengine;

import com.promotionengine.model.Cart;

public interface ICartPriceCalculator {

	double GetCartTotalPrice(Cart cart);

}
