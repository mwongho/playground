package za.co.mwongho.cucumber.buisness.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import za.co.mwongho.cucumber.buisness.domain.model.Product;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository repository;

	@Test
	public void findByName() throws Exception {
		Optional<Product> optionalProduct = this.repository.findByName("apple");
		assertThat(optionalProduct).isNotNull().isNotEmpty();
		
		Product product = optionalProduct.get();
		assertThat(product.getId()).isEqualTo(1L);
		assertThat(product.getName()).isEqualTo("apple");
	}
}
