package com.ab.workingWithDatabase.service.impl;

import java.util.List;

import com.ab.workingWithDatabase.entity.Person;
import com.ab.workingWithDatabase.model.request.PersonRequestModel;
import com.ab.workingWithDatabase.model.request.PersonSearchRequestModel;
import com.ab.workingWithDatabase.model.response.PersonResponseModel;

public interface IPersonService {

	PersonResponseModel addPerson(PersonRequestModel personRequestModel);
	
	List<Person> searchPerson(PersonSearchRequestModel personSearchRequestModel);

}
