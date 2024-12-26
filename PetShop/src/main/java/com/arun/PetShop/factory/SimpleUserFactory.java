package com.arun.PetShop.factory;

import com.arun.PetShop.exception.AllReadyCreatedException;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.UserRepository;
import com.arun.PetShop.request.RegistractionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimpleUserFactory  implements UserFactory{

    private final UserRepository userRepository;
    private final AdminFactory  adminFactory;
    private final PatientFactory patientFactory;
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
