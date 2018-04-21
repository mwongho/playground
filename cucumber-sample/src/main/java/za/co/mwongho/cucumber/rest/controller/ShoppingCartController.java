package za.co.mwongho.cucumber.rest.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.mwongho.cucumber.buisness.domain.model.Cart;
import za.co.mwongho.cucumber.buisness.domain.model.Product;
import za.co.mwongho.cucumber.buisness.repository.ProductRepository;
import za.co.mwongho.cucumber.rest.domain.model.OrderLine;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingCartController.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	private Cart cart;
	
	@GetMapping
    public Cart getCart() {
		if(this.cart == null) {
			this.cart = new Cart();
		}
        return this.cart;
    }
	
	@PostMapping
    public void addProduct(@RequestBody final OrderLine orderLine) {
		LOG.debug("orderLine :{}", orderLine);
		Optional<Product> optionalProduct = this.productRepository.findByName(orderLine.getProductName());
		LOG.debug("optionalProduct :{}", optionalProduct);
		if(optionalProduct.isPresent()) {
			int quantity = orderLine.getQuantity();
			Product product = optionalProduct.get();
			for (int i = 0; i < quantity; i++) {
				getCart().addProduct(product);
			}
		}
    }
	
	
	@DeleteMapping
    public void clearCart() {
        getCart().clear();
    }
}
