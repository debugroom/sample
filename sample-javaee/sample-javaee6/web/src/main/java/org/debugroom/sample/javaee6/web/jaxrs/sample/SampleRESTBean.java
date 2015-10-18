package org.debugroom.sample.javaee6.web.jaxrs.sample;

import java.util.List;

import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.debugroom.sample.javaee6.service.SampleService;
import org.debugroom.sample.javaee6.domain.model.entity.User;

@Path("/resource/users")
@RequestScoped
public class SampleRESTBean {

	@EJB
	@Inject
	private SampleService sampleService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAllUsers(){
		return sampleService.findUsersService();
	}
	
}
