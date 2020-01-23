package com.ab.workingWithDatabase.model.request;

import com.ab.workingWithDatabase.util.AddressType;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressRequestModel {

	@JsonProperty(value = "area")
	private String area;
	
	@JsonProperty(value = "pincode")
	private Integer pincode;
	
	@JsonProperty(value = "city")
	private String city;
	
	@JsonProperty(value = "state")
	private String state;
	
	@JsonProperty(value = "country")
	private String country;
	
	@JsonProperty(value = "addressType")
	private AddressType addressType;
}
