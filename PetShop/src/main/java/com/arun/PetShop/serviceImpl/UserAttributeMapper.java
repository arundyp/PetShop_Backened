package com.arun.PetShop.service;

import com.arun.PetShop.model.User;
import com.arun.PetShop.request.RegistractionRequest;
import org.springframework.stereotype.Component;

@Component
public class UserAttributeMapper {
    public void setCommonAttribute(RegistractionRequest source, User target){
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        target.setGender(source.getGender());
        target.setPhoneNo(source.getPhoneNo());
        target.setSpecilization(source.getSpecilization());
        target.setUserType(source.getUserType());
        target.setEnable(source.isEnable());


    }
}
