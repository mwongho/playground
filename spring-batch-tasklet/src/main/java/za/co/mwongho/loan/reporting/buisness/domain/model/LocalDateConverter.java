package za.co.mwongho.loan.reporting.buisness.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class LocalDateConverter extends  AbstractBeanField<LocalDate>{

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	}

}
