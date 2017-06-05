package org.debugroom.sample.docker.boot.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.debugroom.sample.docker.boot.domain.service.SampleService;

@RequestMapping("/api/v1")
@RestController
public class SampleRestController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<?> getUsers(){
		return sampleService.getUsers();
	}
	
}
