package za.co.mwongho.cucumber.buisness.domain.model;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class CartTest {
	
	private static BigDecimal getPrice(final String stringPrice) {
		BigDecimal price = new BigDecimal(stringPrice);
		return price.setScale(2, RoundingMode.HALF_UP);
	}

	@Test
	public void getTotal() {
		Cart cart = new Cart();
		cart.addProduct(new Product(1, "apple", getPrice("10.00")));
		cart.addProduct(new Product(2, "pepper", getPrice("0.50")));
		
		assertThat(cart).isNotNull();
		assertThat(cart.getProducts()).isNotNull().isNotEmpty().size().isEqualTo(2);
		assertThat(cart.getTotal()).isNotNull().isEqualTo(getPrice("10.50"));
	}

}
