package com.promotionengine.data;

import java.util.List;

import com.promotionengine.model.Product;

public interface IProductsProvider {

	List<Product> GetProducts();
	Product GetProduct(String sku);
}
