package com.ab.workingWithDatabase.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonSearchRequestModel {

	@JsonProperty(value = "firstName")
	private String firstName;
	
	@JsonProperty(value = "lastName")
	private String lastName;
	
	@JsonProperty(value = "startRangeBirthDate")
	private LocalDate startRangeBirthDate;
	
	@JsonProperty(value = "endRangeBirthDate")
	private LocalDate endRangeBirthDate;
	
	@JsonProperty(value = "mobile")
	private Long mobile;
}
