package com.touhid.onlineshop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.touhid.onlineshop.model.Customer;
import com.touhid.onlineshop.model.Product;

@Component
public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		
		return Customer.class.equals(paramClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
    Customer customer=(Customer) target;
		
    //System.out.println("----------------------error22222--------------");
    
        if(customer.getPassword().length()<6 || customer.getPassword().length()>30){
        	
        	//System.out.println("----------------------error--------------");
        	errors.rejectValue("password", null, "Password must be between 6 and 30 character");
        }
		
	}

	

	
}
