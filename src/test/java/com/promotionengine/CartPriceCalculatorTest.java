package com.promotionengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.promotionengine.data.IProductsProvider;
import com.promotionengine.data.IPromotionsProvider;
import com.promotionengine.model.Cart;
import com.promotionengine.model.Product;
import com.promotionengine.model.Promotion;
import com.promotionengine.model.PromotionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CartPriceCalculatorTest {

	@MockBean
	IProductsProvider productsProvider;

	@MockBean
	IPromotionsProvider promotionsProvider;

	@Autowired
	ICartPriceCalculator cartPriceCalculator;

	@BeforeEach
	void init() {
		// setup test products
		setupTestProducts();
		// setup test promotions
		setupTestPromotions();
	}

	private void setupTestProducts() {
		List<Product> testProducts = new ArrayList<Product>() {
			{
				add(new Product("A", 50));
				add(new Product("B", 30));
				add(new Product("C", 20));
				add(new Product("D", 15));
			}
		};
		// setup get products mock
		when(productsProvider.GetProducts()).thenReturn(testProducts);
		testProducts.forEach((Product p) -> {
			when(productsProvider.GetProduct(p.getSKU_ID())).thenReturn(p);
		});

	}

	private void setupTestPromotions() {
		List<Promotion> testPromotions = new ArrayList<Promotion>();
		testPromotions.add(new Promotion(1, "3 of A's for 130", new HashMap<String, Integer>() {
			{
				put("A", 3);
			}
		}, PromotionType.MULTIPLEITEMSOFSKU, 130d, null));
		testPromotions.add(new Promotion(2, "2 of B's for 45", new HashMap<String, Integer>() {
			{
				put("B", 2);
			}
		}, PromotionType.MULTIPLEITEMSOFSKU, 45d, null));
		testPromotions.add(new Promotion(3, "C & D for 30", new HashMap<String, Integer>() {
			{
				put("C", 1);
				put("D", 1);
			}
		}, PromotionType.COMBINEDSKUS, 30d, null));
		when(promotionsProvider.GetPromotions()).thenReturn(testPromotions);
	}

	@Test
	void ScenarioA() {
		Cart cart = new Cart("A", "B", "C");
		double totalPrice = cartPriceCalculator.GetCartTotalPrice(cart);
		assertEquals(100, totalPrice);
	}

	@Test
	void ScenarioB() {
		Cart cart = new Cart("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C");
		double totalPrice = cartPriceCalculator.GetCartTotalPrice(cart);
		assertEquals(370, totalPrice);
	}

	@Test
	void ScenarioC() {
		Cart cart = new Cart("A", "A", "A", "B", "B", "B", "B", "B", "C","D");
		double totalPrice = cartPriceCalculator.GetCartTotalPrice(cart);
		assertEquals(280, totalPrice);
	}

}
