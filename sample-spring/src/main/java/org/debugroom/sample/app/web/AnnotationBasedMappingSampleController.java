package org.debugroom.sample.app.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.debugroom.sample.domain.service.common.ServiceExecutor;

/**
 * 
 * @author org.debugroom
 *
 */
@Controller
@RequestMapping(value="/annotationBasedMappingSample")
public class AnnotationBasedMappingSampleController {

	@Autowired
	@Qualifier("annotatedServiceExecutorImpl")
	private ServiceExecutor serviceExecutor;
	
	@Autowired
	private MessageSource messageSource;
	
	@ModelAttribute
	public AnnotatedSampleParameters setUp(){
		// executeGetMethod() argument parameter initialization.
		AnnotatedSampleParameters annotatedSampleParameters = new AnnotatedSampleParameters();
		annotatedSampleParameters.setUserId("00000000");
		annotatedSampleParameters.setUserName("(・∀・)");
		return annotatedSampleParameters;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView executeGetMethod(
			@ModelAttribute @Validated AnnotatedSampleParameters annotatedSampleParameters, 
			@RequestParam("target") String target,
			Errors errors, BindingResult bindingResult){
		
		ModelAndView modelAndView = new ModelAndView();
		if(errors.hasErrors()){
			MessageSourceAccessor messageSourceAccessor 
			= new MessageSourceAccessor(this.messageSource);
			System.out.println(messageSourceAccessor
					.getMessage("error.input.sampleParameters"));
			modelAndView.setViewName("home.jsp");
		}
		serviceExecutor.executeService(target);
		modelAndView.setViewName("sample.jsp");
		
		return modelAndView;
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(
				Date.class, "date", new CustomDateEditor(dateFormat, false));
	}
}
