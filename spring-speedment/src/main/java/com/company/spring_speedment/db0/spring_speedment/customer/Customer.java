package com.company.spring_speedment.db0.spring_speedment.customer;

import com.company.spring_speedment.db0.spring_speedment.customer.generated.GeneratedCustomer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * The main interface for entities of the {@code customer}-table in the
 * database.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author mwongho
 */
@JsonDeserialize(as = CustomerImpl.class)
public interface Customer extends GeneratedCustomer {
    
    
}