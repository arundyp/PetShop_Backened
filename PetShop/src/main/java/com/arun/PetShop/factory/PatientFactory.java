package com.arun.PetShop.factory;

import com.arun.PetShop.model.Patient;
import com.arun.PetShop.repository.PatientRepository;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.service.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory implements UserFactory{
    private final PatientRepository patientRepository;
    private final UserAttributeMapper userAttributeMapper;
    @Override
    public Patient createUser(RegistractionRequest request) {
        Patient patient = new Patient();
        this.userAttributeMapper.setCommonAttribute(request,patient);
        return this.patientRepository.save(patient);
    }
}
