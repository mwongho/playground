package com.company.spring_speedment.db0.spring_speedment.customer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.spring_speedment.db0.spring_speedment.customer.generated.GeneratedCustomerController;

/**
 * REST controller logic
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author mwongho
 */
@RestController
public class CustomerController extends GeneratedCustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    
	   @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		   Optional<Customer> optionalCustomer = manager.stream().filter(Customer.ID.equal(id)).findAny();
	        if (optionalCustomer.isPresent()) {
	        	 return new ResponseEntity<Object>(optionalCustomer.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<Object>("User with id " + id  + " not found", HttpStatus.NOT_FOUND);
	        }
	       
	    }
	   
	   @RequestMapping(value = "/customer", method = RequestMethod.POST)
	   public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		   Customer persistedCustomer = manager.persist(customer);
		   return new ResponseEntity<Object>(persistedCustomer, HttpStatus.CREATED);
	   }
	   
	   @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		   Optional<Customer> optionalCustomer = manager.stream().filter(Customer.ID.equal(customer.getId())).findAny();
		   if (optionalCustomer.isPresent()) {
			   Customer updatedCustomer = manager.update(customer);
			   return new ResponseEntity<Object>(updatedCustomer, HttpStatus.OK);
		   } else {
			   return new ResponseEntity<Object>("User with id " + id  + " not found", HttpStatus.NOT_FOUND);
		   }

	   }
	   
	   @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	   public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id) {
		   logger.info("deleteCustomer");
		   Optional<Customer> optionalCustomer = manager.stream().filter(Customer.ID.equal(id)).findAny();
		   if (optionalCustomer.isPresent()) {
			   manager.remove(optionalCustomer.get());
			   return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		   } else {
			   return new ResponseEntity<Object>("User with id " + id  + " not found", HttpStatus.NOT_FOUND);
		   }

	   }
	   
}