package com.promotionengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.promotionengine.data.IProductsProvider;
import com.promotionengine.data.IPromotionsProvider;
import com.promotionengine.model.Cart;
import com.promotionengine.model.Product;
import com.promotionengine.model.Promotion;
import com.promotionengine.model.PromotionalProductsItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartPriceCalculator implements ICartPriceCalculator {

	@Autowired
	IProductsProvider productPrvider;

	@Autowired
	IPromotionsProvider promotionsProvider;

	@Override
	public double GetCartTotalPrice(Cart cart) {
		cart = setCartProductsAndPromotions(cart);
		return calculateTotalPrice(cart);
	}

	private Cart setCartProductsAndPromotions(Cart cart) {
		List<String> cartSKUs = cart.getSKUs();

		// get available promotions
		List<Promotion> promotions = promotionsProvider.GetPromotions();

		// apply promotions
		for (Promotion promo : promotions) {
			boolean applyPromtion = true;

			//keep applying the same promotion as many times as it can
			while (applyPromtion) {
				List<String> promoSKUs = new ArrayList<String>();

				for (Map.Entry<String, Integer> rule : promo.getProductCountRules().entrySet()) {
					int skuCount = Collections.frequency(cartSKUs, rule.getKey());

					// if rule doesn't match then skip
					if (skuCount >= rule.getValue()) {
						promoSKUs.addAll(cartSKUs.stream()
								.filter(sku -> sku == rule.getKey())
								.limit(rule.getValue())
								.collect(Collectors.toList()));
					} else {
						applyPromtion = false;
						break;
					}
				}

				// if all rules of promo match then add promo product item to cart
				// along with products
				if (applyPromtion) {
					// remove promo matched skus from cartSKUs
					promoSKUs.forEach(pSKU -> cartSKUs.remove(pSKU));

					List<Product> promoProds = getProductsForSKUs(promoSKUs);
					cart.addPromotionalProductsItem(new PromotionalProductsItem(promo, promoProds));
				}
			}

		}

		// add non promo products to cart
		cart.setNonPromotionalProducts(getProductsForSKUs(cartSKUs));

		return cart;
	}

	private List<Product> getProductsForSKUs(List<String> skus) {
		List<Product> products = new ArrayList<>();
		for (String sku : skus) {
			products.add(productPrvider.GetProduct(sku));
		}
		return products;
	}

	private double calculateTotalPrice(Cart cart) {

		double totalPrice = 0;

		// non promotional products
		for (Product product : cart.getNonPromotionalProducts()) {
			totalPrice += product.getPrice();
		}

		// promotional products
		for (PromotionalProductsItem promoProds : cart.getPromotionalProductsItems()) {
			totalPrice += promoProds.getPromotion().getPromotionalFixedPrice();
		}

		return totalPrice;
	}

}
