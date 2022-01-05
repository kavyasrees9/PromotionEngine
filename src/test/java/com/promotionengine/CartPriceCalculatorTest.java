package com.promotionengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.promotionengine.data.IProductsProvider;
import com.promotionengine.data.IPromotionsProvider;
import com.promotionengine.model.Cart;
import com.promotionengine.model.Product;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CartPriceCalculatorTest {

	@MockBean
	IProductsProvider productsProvider;

	@MockBean
	IPromotionsProvider promotionsProvider;

	@Autowired
	ICartPriceCalculator cartPriceCalculator;
	
	@BeforeAll
	void init() {
		// setup test products
		setupTestProducts();
	}

	private void setupTestProducts() {
		// TODO Auto-generated method stub
		List<Product> testProducts= new ArrayList<Product>() {
			{
				add(new Product("A",50));
				add(new Product("B",30));
				add(new Product("C",20));
				add(new Product("D",15));
			}
		};
		// setup get products mock
		when(productsProvider.GetProducts()).thenReturn(testProducts);
		testProducts.forEach((Product p)->{
			when(productsProvider.GetProduct(p.getSKU_ID())).thenReturn(p);
		});
		
	}



	@Test
	void ScenarioA() {
		Cart cart = new Cart("A", "B", "C");

		double totalPrice = cartPriceCalculator.GetCartTotalPrice(cart);
		assertEquals(100, totalPrice);
	}
}
