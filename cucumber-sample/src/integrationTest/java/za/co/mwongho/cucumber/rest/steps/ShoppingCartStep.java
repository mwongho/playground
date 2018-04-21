package za.co.mwongho.cucumber.rest.steps;

import org.springframework.http.HttpStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import za.co.mwongho.cucumber.buisness.domain.model.Cart;
import za.co.mwongho.cucumber.buisness.domain.model.Product;
import za.co.mwongho.cucumber.rest.SpringBootBaseIntegrationTest;
import za.co.mwongho.cucumber.rest.domain.model.OrderLine;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartStep extends SpringBootBaseIntegrationTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartStep.class);
	
	private static final String SERVICE_ENDPOINT = "/cart";
	
	Cart get() {
        return this.restTemplate.getForEntity(SERVICE_ENDPOINT, Cart.class).getBody();
    }
	
	void delete() {
		this.restTemplate.delete(SERVICE_ENDPOINT);
    }
	
	HttpStatus post(OrderLine orderLine) {
		return this.restTemplate.postForEntity(SERVICE_ENDPOINT, orderLine, Void.class).getStatusCode();
	}
	
	@Given("^an empty shopping cart$")
	public void an_empty_shopping_cart() {
		delete();
		Cart cart = get();
		assertThat(cart).isNotNull();
		assertThat(cart.getProducts().isEmpty()).isTrue();
	}

	@When("^customer adds (\\d+) (\\w+) to the shopping cart$")
	public void customer_adds_apple_to_the_shopping_cart(final int quantity, final String productName) {
		OrderLine orderLine = new OrderLine(productName, quantity);
		HttpStatus httpStatus = post(orderLine);
		assertThat(httpStatus).isEqualTo(HttpStatus.OK);
	}
	
	@Then("^the shopping cart should contain only (\\d+) (\\w+)$")
	public void the_shopping_cart_should_contain(final int quantity, final String productName) {
		Cart cart = get();
		assertThat(cart).isNotNull();
		assertThat(cart.getProducts()).isNotEmpty();
		List<Product> products = cart.getProducts();
		assertThat(products.stream().filter(product -> product.getName().equals(productName)).count()).isEqualTo(quantity);
	}
	
	@Then("^the shopping cart Total Amount should be (\\d+\\.\\d{0,2})$")
	public void the_shopping_cart_Total_Amount(final BigDecimal total) {
		Cart cart = get();
		assertThat(cart).isNotNull();
		assertThat(cart.getProducts()).isNotEmpty();
		assertThat(cart.getTotal()).isEqualTo(cart.getTotal());
	}
}
