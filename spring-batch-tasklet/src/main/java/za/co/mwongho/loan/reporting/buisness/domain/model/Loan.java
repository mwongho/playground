package za.co.mwongho.loan.reporting.buisness.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper=false)
@Getter
@Setter
public class Loan extends DomainObject {

	@CsvBindByName
	private String msisdn;
	@CsvBindByName
	private String network;
	@CsvCustomBindByName(converter = LocalDateConverter.class)
	private LocalDate date;
	@CsvBindByName()
	private String product;
	@CsvBindByName()
	private BigDecimal amount;
	
	public Loan() {
		super();
	}
	
	public Loan(String msisdn, String network, LocalDate date, String product, BigDecimal amount) {
		super();
		this.msisdn = msisdn;
		this.network = network;
		this.date = date;
		this.product = product;
		this.amount = amount;
	}
	
}
