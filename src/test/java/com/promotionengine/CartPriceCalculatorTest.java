package com.promotionengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.promotionengine.data.Cart;
import com.promotionengine.data.ICartPriceCalculator;
import com.promotionengine.data.IProductsProvider;
import com.promotionengine.data.IPromotionsProvider;

@SpringBootTest
public class CartPriceCalculatorTest {

	@MockBean
	IProductsProvider productsProvider;

	@MockBean
	IPromotionsProvider promotionsProvider;

	@Autowired
	ICartPriceCalculator cartPriceCalculator;

	@Test
	void ScenarioA() {
		Cart cart = new Cart("A", "B", "C");

		double totalPrice = cartPriceCalculator.GetCartTotalPrice(cart);
		assertEquals(100, totalPrice);
	}
}
