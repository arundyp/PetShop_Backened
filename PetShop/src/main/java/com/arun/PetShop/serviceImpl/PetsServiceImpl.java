package com.arun.PetShop.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.model.Pets;
import com.arun.PetShop.repository.PetsRepository;
import com.arun.PetShop.service.PetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetsServiceImpl implements PetService {
	private final PetsRepository petsRepository;

	@Override
	public List<Pets> savePetForAppointment(List<Pets> pets) {
		List<Pets> saveAll = this.petsRepository.saveAll(pets);
		return saveAll;
	}

	@Override
	public Pets updatePets(Long id,Pets pet) {
		Pets exitPets = getPetsByID(id);
		exitPets.setName(pet.getName() != null ? pet.getName() : exitPets.getName());
		exitPets.setAge(pet.getAge());
		exitPets.setBreed(pet.getBreed() != null ? pet.getBreed() : exitPets.getBreed());
		exitPets.setBreed(pet.getBreed() != null ? pet.getBreed() : exitPets.getBreed());
		exitPets.setColor(pet.getBreed() != null ? pet.getBreed() : exitPets.getBreed());
		exitPets.setType(pet.getBreed() != null ? pet.getBreed() : exitPets.getBreed());
		return this.petsRepository.save(exitPets);
	}

	@Override
	public void deletePets(Long id) {
		Pets pets = this.petsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pets not available"));
		this.petsRepository.delete(pets);

	}

	@Override
	public Pets getPetsByID(Long id) {
		Pets pets = this.petsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pets not available"));
		return pets;
	}

}
