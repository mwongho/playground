package za.co.mwongho.cucumber.buisness.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.mwongho.cucumber.buisness.domain.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Optional<Product> findByName(String name);
	
}
