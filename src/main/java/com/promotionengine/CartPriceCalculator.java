package com.promotionengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.promotionengine.data.IProductsProvider;
import com.promotionengine.model.Cart;
import com.promotionengine.model.OrderItem;
import com.promotionengine.model.Product;

@Component
public class CartPriceCalculator implements ICartPriceCalculator {

	@Autowired
	IProductsProvider productPrvider;

	@Override
	public double GetCartTotalPrice(Cart cart) {
		List<OrderItem> orderItems = getOrderItems(cart);
		return calculateTotalPrice(orderItems);
	}

	private double calculateTotalPrice(List<OrderItem> orderItems) {
		double totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += (orderItem.getProduct().getPrice() * orderItem.getQuantity());
		}
		return totalPrice;
	}

	private List<OrderItem> getOrderItems(Cart cart) {
		List<OrderItem> itemslist = new ArrayList<OrderItem>();

		Map<String, Long> productAndCount = cart.getSKUs().stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		for (String sku : productAndCount.keySet()) {
			Product product = productPrvider.GetProduct(sku);
			itemslist.add(new OrderItem(product, productAndCount.get(sku)));
		}
		 
		return itemslist;
	}

}
