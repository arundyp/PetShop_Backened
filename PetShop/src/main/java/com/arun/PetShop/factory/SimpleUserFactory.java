package com.arun.PetShop.factory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.arun.PetShop.exception.AllReadyCreatedException;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.UserRepository;
import com.arun.PetShop.request.RegistractionRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Primary
public class SimpleUserFactory  implements UserFactory{

	@Qualifier("userRepository")
    private final UserRepository userRepository;
	@Qualifier("adminFactory")
    private final AdminFactory  adminFactory;
	@Qualifier("patientFactory")
    private final PatientFactory patientFactory;
	 @Qualifier("veteriarianFactory")
    private final VeteriarianFactory veteriarianFactory;
    @Override
    public User createUser(RegistractionRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
        {
            throw new AllReadyCreatedException("Opps "+request.getEmail()+" already exists.");
        }
        switch (request.getUserType())
        {
            case "VET"->{
                return veteriarianFactory.createUser(request);
            }
            case "Adm"-> {
                return  adminFactory.createUser(request);
            }
            case "Pat"->{
                return patientFactory.createUser(request);
            }
            default -> {
                return null;
            }
        }

    }
}
