package za.co.mwongho.cucumber.buisness.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Product {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private BigDecimal price;

	public Product() {
		super();
	}

	public Product(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Product(long id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.price.setScale(2, RoundingMode.HALF_UP);
	}

}
