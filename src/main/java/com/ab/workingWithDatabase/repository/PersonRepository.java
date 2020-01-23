package com.ab.workingWithDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.workingWithDatabase.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
