package com.arun.PetShop.service;

import java.util.List;

import com.arun.PetShop.model.User;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.utils.UpdateUserRequest;

public interface UserService {
	User registerUser(RegistractionRequest request);
	void deleteUser(long userid);
	User updateUser(long userId,User request);
	List<User> getAllUsers();
	User getSingleUser(Long userId);

}
