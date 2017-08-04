package com.kutash.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

	private int min;
	

	public void initialize(ValidEmail constraintAnnotation) {
		min = constraintAnnotation.min();
	}


	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email.length() < min) {
			return false;
		}
		
		/*if(!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}*/

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()){
		    return false;
        }
		
		return true;
	}

}
