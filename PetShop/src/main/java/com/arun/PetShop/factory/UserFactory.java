package com.arun.PetShop.factory;

import com.arun.PetShop.model.User;
import com.arun.PetShop.request.RegistractionRequest;

public interface UserFactory {
    public User createUser(RegistractionRequest request);
}
