package com.arun.PetShop.factory;

import com.arun.PetShop.model.User;
import com.arun.PetShop.model.Veterinarian;
import com.arun.PetShop.repository.VeteriarianRepository;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.service.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeteriarianFactory implements UserFactory{
    private final VeteriarianRepository veteriarianRepository;
    private UserAttributeMapper userAttributeMapper;
    @Override
    public Veterinarian createUser(RegistractionRequest request) {
        Veterinarian veterinarian = new Veterinarian();
        veterinarian.setSpecilization(request.getSpecilization());
        this.userAttributeMapper.setCommonAttribute(request,veterinarian);
        return veteriarianRepository.save(veterinarian);
    }
}
