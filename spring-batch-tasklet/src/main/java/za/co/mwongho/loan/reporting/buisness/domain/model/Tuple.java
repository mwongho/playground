package za.co.mwongho.loan.reporting.buisness.domain.model;

import java.time.Month;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper=false)
@Getter
@Setter
public class Tuple extends DomainObject{
	
	private String network;
	private String product;
	private Month month;
	
	public Tuple(String network, String product) {
		super();
		this.network = network;
		this.product = product;
	}

	public Tuple(String network, String product, Month month) {
		super();
		this.network = network;
		this.product = product;
		this.month = month;
	}
	
}
