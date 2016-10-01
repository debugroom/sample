package org.debugroom.sample.spring.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.debugroom.sample.spring.boot.entity.Company;
import org.debugroom.sample.spring.boot.service.SampleService;

@RestController
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="/sample", method=RequestMethod.GET)
	public ResponseEntity<List<Company>> sample(){
		return ResponseEntity.status(HttpStatus.OK).body(
				sampleService.getCompanies());
	}

}
