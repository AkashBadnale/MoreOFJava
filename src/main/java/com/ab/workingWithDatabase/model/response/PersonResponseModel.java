package com.ab.workingWithDatabase.model.response;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.ab.workingWithDatabase.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonResponseModel {

	@JsonProperty(value = "firstName")
	private String firstName;
	
	@JsonProperty(value = "lastName")
	private String lastName;
	
	@JsonProperty(value = "birthDate")
	private LocalDate birthDate;
	
	@JsonProperty(value = "mobile")
	private Long mobile;
	
	@JsonProperty(value = "address")
	private Set<Address> address = new HashSet<>();
}
