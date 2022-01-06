package com.promotionengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.promotionengine.data.IProductsProvider;
import com.promotionengine.model.Cart;
import com.promotionengine.model.PromotionalProductsItem;
import com.promotionengine.model.Product;

@Component
public class CartPriceCalculator implements ICartPriceCalculator {

	@Autowired
	IProductsProvider productPrvider;

	@Override
	public double GetCartTotalPrice(Cart cart) {
		setCartProducts(cart);
		return calculateTotalPrice(cart);
	}

	private void setCartProducts(Cart cart) {
		List<Product> allProducts = new ArrayList<Product>();

		for (String sku : cart.getSKUs()) {
			allProducts.add(productPrvider.GetProduct(sku));
		}

		cart.setNonPromotionalProducts(allProducts);
	}

	private double calculateTotalPrice(Cart cart) {

		double totalPrice = 0;

		// non promotional products
		for (Product product : cart.getNonPromotionalProducts()) {
			totalPrice += product.getPrice();
		}

		return totalPrice;
	}

	/*
	 * private List<PromotionalProductsItem> getOrderItems(Cart cart) {
	 * List<PromotionalProductsItem> itemslist = new
	 * ArrayList<PromotionalProductsItem>();
	 * 
	 * Map<String, Long> productAndCount = cart.getSKUs().stream()
	 * .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	 * 
	 * for (String sku : productAndCount.keySet()) { Product product =
	 * productPrvider.GetProduct(sku); itemslist.add(new
	 * PromotionalProductsItem(product, productAndCount.get(sku))); }
	 * 
	 * return itemslist; }
	 */

}
