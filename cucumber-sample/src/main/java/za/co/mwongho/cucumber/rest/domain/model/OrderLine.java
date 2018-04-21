package za.co.mwongho.cucumber.rest.domain.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderLine {
	private String productName;
	private int quantity;

	public OrderLine() {
		super();

	}

	public OrderLine(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}

}
