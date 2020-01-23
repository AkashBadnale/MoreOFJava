package com.ab.workingWithDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.workingWithDatabase.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
