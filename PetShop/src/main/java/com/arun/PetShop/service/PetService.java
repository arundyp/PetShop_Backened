package com.arun.PetShop.service;

import java.util.List;

import com.arun.PetShop.model.Pets;

public interface PetService {
	List<Pets> savePetForAppointment(List<Pets> pets);
	Pets updatePets(Long id,Pets pet);
	void deletePets(Long id);
	Pets getPetsByID(Long id);

}
