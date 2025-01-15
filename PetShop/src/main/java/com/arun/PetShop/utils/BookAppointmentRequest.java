package com.arun.PetShop.utils;

import java.util.List;

import com.arun.PetShop.model.Appointment;
import com.arun.PetShop.model.Pets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookAppointmentRequest {
	private Appointment appointment;
	private List<Pets> pets;

}
