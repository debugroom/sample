package org.debugroom.sample.javaee6.web.sample;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SampleValidator implements ConstraintValidator<SampleAnnotation, String>{

	private int from;
	private int to;

	@Override
	public void initialize(SampleAnnotation sampleAnnotation) {
		from = sampleAnnotation.from();
		to = sampleAnnotation.to();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.length() != 8){
			return false;
		}
		char top = value.toLowerCase().charAt(0);
		if(top != 'a' && top != 'b' && top != 'c'){
			return false;
		}
		for(int i = 1 ; i < 8 ; i++){
			if(!Character.isDigit(value.charAt(i))){
				return false;
			}
		}
		if(Integer.parseInt(value.substring(1, 5)) < from){
			return false;
		}
		if(Integer.parseInt(value.substring(1, 5)) > to){
			return false;
		}
		return true;
	}

}
