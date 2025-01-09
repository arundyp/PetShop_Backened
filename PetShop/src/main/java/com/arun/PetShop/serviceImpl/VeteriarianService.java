package com.arun.PetShop.service;

import com.arun.PetShop.factory.UserFactory;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.VeteriarianRepository;
import com.arun.PetShop.request.RegistractionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeteriarianService {
    private final VeteriarianRepository veteriarianRepository;
    private final UserFactory userFactory;

    public User add(RegistractionRequest request)
    {
       return userFactory.createUser(request);
    }
}
