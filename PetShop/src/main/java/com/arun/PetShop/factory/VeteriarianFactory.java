package com.arun.PetShop.factory;

import org.springframework.stereotype.Service;

import com.arun.PetShop.model.Veterinarian;
import com.arun.PetShop.repository.VeteriarianRepository;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.service.UserAttributeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeteriarianFactory implements UserFactory {
	private final VeteriarianRepository veteriarianRepository;
	private final UserAttributeMapper userAttributeMapper;

	@Override
	public Veterinarian createUser(RegistractionRequest request) {
		Veterinarian veterinarian = new Veterinarian();
		veterinarian.setSpecilization(request.getSpecilization());
		this.userAttributeMapper.setCommonAttribute(request, veterinarian);
		return veteriarianRepository.save(veterinarian);
	}
}
