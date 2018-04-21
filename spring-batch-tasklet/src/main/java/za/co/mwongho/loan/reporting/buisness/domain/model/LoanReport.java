package za.co.mwongho.loan.reporting.buisness.domain.model;

import java.math.BigDecimal;
import java.time.Month;

import com.opencsv.bean.CsvBindByName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mwongho
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class LoanReport extends DomainObject {
	
	@CsvBindByName
	private String network;
	@CsvBindByName
	private String product;
	@CsvBindByName
	private Month month; 
	@CsvBindByName
	private long count;
	/**
	 * 
	 */
	@CsvBindByName
	private BigDecimal totalAmount;
	
	public LoanReport() {
		super();
	}
	
	public LoanReport(Tuple tuple, long count, BigDecimal totalAmount) {
		super();
		this.network = tuple.getNetwork();
		this.product = tuple.getProduct();
		this.month = tuple.getMonth();
		this.count = count;
		this.totalAmount = totalAmount.setScale(2);
	}

}
