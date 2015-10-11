package org.debugroom.sample.app.validator;

import org.debugroom.sample.app.common.SampleParameters;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * @author org.debugroom
 *
 */
public class SampleValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleParameters.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		SampleParameters sampleParameters = (SampleParameters)object;
		if(!StringUtils.hasLength(sampleParameters.getUserId())){
			errors.rejectValue("userId", "error.required");
		}
		if(errors.hasErrors()){
			errors.reject("error.input.sampleParameters");
		}
	}

}
