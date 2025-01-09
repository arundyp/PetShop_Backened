package com.arun.PetShop.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNo;
	private String userType;
	private boolean isEnable;
	private String specilization;

}
