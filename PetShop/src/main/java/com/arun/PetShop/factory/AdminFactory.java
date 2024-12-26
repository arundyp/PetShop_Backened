package com.arun.PetShop.factory;

import com.arun.PetShop.model.Admin;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.AdminRepository;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.service.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFactory implements UserFactory{
    private final AdminRepository adminRepository;
    private final UserAttributeMapper userAttributeMapper;
    @Override
    public Admin createUser(RegistractionRequest request) {
        Admin admin = new Admin();
        this.userAttributeMapper.setCommonAttribute(request,admin);
        return this.adminRepository.save(admin);
    }
}
