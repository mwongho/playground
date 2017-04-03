package test.za.co.mwongho.spring_speedment.db0.spring_speedment.customer;

import com.company.spring_speedment.db0.spring_speedment.customer.Customer;
import com.company.spring_speedment.db0.spring_speedment.customer.CustomerImpl;

public class CustomerBuilder {
	
	  private Customer customer = new CustomerImpl();
	  
	  public CustomerBuilder id(long id) {
		this.customer.setId(id);
	    return this;
	  }
	  
	  public CustomerBuilder name(String name) {
	    this.customer.setName(name);
	    return this;
	  }
	  
	  public Customer build() {
	    return this.customer;
	  }

}
