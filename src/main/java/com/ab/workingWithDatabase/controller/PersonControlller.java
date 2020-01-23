package com.ab.workingWithDatabase.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ab.workingWithDatabase.entity.Person;
import com.ab.workingWithDatabase.model.request.PersonRequestModel;
import com.ab.workingWithDatabase.model.request.PersonSearchRequestModel;
import com.ab.workingWithDatabase.model.response.PersonResponseModel;
import com.ab.workingWithDatabase.service.impl.IPersonService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PersonControlller {

	private static final String ENDPOINT = "/person";
	
	@Autowired
	private IPersonService personService;
	
	@ApiOperation(value="ADD PERSON")
	@PostMapping( value=ENDPOINT,
			      consumes=MediaType.APPLICATION_JSON_VALUE,
			      produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonResponseModel> addPerson( @RequestBody PersonRequestModel personRequestModel ) {
		ModelMapper modelMapper = new ModelMapper();
		PersonResponseModel personResponseModel = modelMapper.map( personService.addPerson(personRequestModel), PersonResponseModel.class);
		return new ResponseEntity<PersonResponseModel>( personResponseModel, HttpStatus.CREATED);
	}
	
	@ApiOperation(value="SEARCH PERSONs")
	@PostMapping( value=ENDPOINT + "/search",
			      consumes=MediaType.APPLICATION_JSON_VALUE,
			      produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> searchPersons( @RequestBody PersonSearchRequestModel personSearchRequestModel ) {
		List<Person> persons = personService.searchPerson(personSearchRequestModel);
	    return new ResponseEntity<List<Person>>( persons , HttpStatus.CREATED);
	}
}
