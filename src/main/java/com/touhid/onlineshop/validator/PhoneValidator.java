package com.touhid.onlineshop.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone,String>{

	@Override
	public void initialize(Phone arg0) {
		
		//If we are using some resource such as DataSource, we can initialize them in the initialize() method.
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
		//System.out.println("---------------------------------------------------:"+phoneNumber);
		if (phoneNumber==null) {
			//System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
			return false;
		}
		
		if (phoneNumber.matches("01[5-9]\\d{8}$")) {
			//System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
			return true;
					 
		}
		//System.out.println("sssssssssssssssssssssssssssssssssssssssssssssss");
		return false;
	}

}
