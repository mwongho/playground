package za.co.mwongho.cucumber.buisness.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.ToString;
import za.co.mwongho.cucumber.rest.controller.ShoppingCartController;

@Data
@ToString
public class Cart {
	private static final Logger LOG = LoggerFactory.getLogger(Cart.class);
	private List<Product> products = new ArrayList<>();

	public void addProduct(final Product product) {
		this.products.add(product);
	}
	
	public void clear() {
		products.clear();
	}
	
	public BigDecimal getTotal() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Product product : this.products) {
			sum = sum.add(product.getPrice());
		}
		sum = sum.setScale(2, RoundingMode.HALF_UP);
		LOG.debug("sum :{}", sum);
		return sum;
	}
}
