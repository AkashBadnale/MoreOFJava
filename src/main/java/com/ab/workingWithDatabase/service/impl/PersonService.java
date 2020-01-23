package com.ab.workingWithDatabase.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.workingWithDatabase.entity.Person;
import com.ab.workingWithDatabase.model.request.PersonRequestModel;
import com.ab.workingWithDatabase.model.request.PersonSearchRequestModel;
import com.ab.workingWithDatabase.model.response.PersonResponseModel;
import com.ab.workingWithDatabase.repository.PersonRepository;

@Service
@Transactional
public class PersonService implements IPersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public PersonResponseModel addPerson(PersonRequestModel personRequestModel) {
		/**
		Person person = new Person();
	    person.setFirstName(personRequestModel.getFirstName());
		person.setLastName(personRequestModel.getLastName());
		person.setBirthDate(personRequestModel.getBirthDate());
		person.setMobile(personRequestModel.getMobile());
		person.setAddress(personRequestModel.getAddress());
		
		Person savedPerson = personRepository.saveAndFlush(person);
		
		PersonResponseModel personResponseModel = new PersonResponseModel();
		personResponseModel.setFirstName(savedPerson.getFirstName());
		personResponseModel.setLastName(savedPerson.getLastName());
		personResponseModel.setBirthDate(savedPerson.getBirthDate());
		personResponseModel.setMobile(savedPerson.getMobile());
		personResponseModel.setAddress(savedPerson.getAddress());
		
		return personResponseModel;
		*/
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Person person = modelMapper.map(personRequestModel, Person.class);
		PersonResponseModel personResponseModel = modelMapper.map( personRepository.saveAndFlush(person), PersonResponseModel.class );
		return personResponseModel;
	}

	/**
	 *  This method is used for 'Dynamic Search'.
	 *  Means PersonSearchRequestModel has four variables, based on any combination of variables a search will happen. 
	 */
	@Override
	public List<Person> searchPerson(PersonSearchRequestModel personSearchRequestModel) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = criteriaQuery.from(Person.class);
		
		String firstName = personSearchRequestModel.getFirstName();
		String lastName = personSearchRequestModel.getLastName();
		LocalDate startRangeDateOfBirth = personSearchRequestModel.getStartRangeBirthDate();
		LocalDate endRangeDateOfBirth = personSearchRequestModel.getEndRangeBirthDate();
		Long mobile = personSearchRequestModel.getMobile();
		
		/*
		 *  Adding search criteria's for query using CriteriaBuilder
		 */
		List<Predicate> searchCriterias = new ArrayList<>();
		
		if( (firstName != "") && (firstName != null) ) {
			searchCriterias.add( criteriaBuilder.like( root.get("firstName"), "%"+firstName+"%") );
		}
		if( (lastName != "") && (lastName != null) ) {
			searchCriterias.add( criteriaBuilder.like( root.get("lastName"), "%"+lastName+"%") );
		}
		if(  startRangeDateOfBirth!=null && endRangeDateOfBirth!=null && startRangeDateOfBirth.isAfter(endRangeDateOfBirth) ) {
			searchCriterias.add( criteriaBuilder.between( root.get("birthDate"), startRangeDateOfBirth, endRangeDateOfBirth) );
		}
		if( mobile!=null && mobile!=0 ) {
			searchCriterias.add( criteriaBuilder.equal( root.get("mobile"), mobile) );
		}
		criteriaQuery.select( root ).where( criteriaBuilder.and( searchCriterias.toArray(new Predicate[searchCriterias.size()]) ));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
