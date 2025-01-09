package com.arun.PetShop.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.factory.UserFactory;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.UserRepository;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.service.UserService;
import com.arun.PetShop.utils.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserFactory userFactory;
	private final UserRepository userRepository;

	@Override
	public User registerUser(RegistractionRequest request) {
		return userFactory.createUser(request);
	}

	@Override
	public void deleteUser(long userid) {

		User user = this.userRepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("This user is not present" + userid));

		this.userRepository.delete(user);

	}

	@Override
	public User updateUser(long userId, User request) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("This user is not present" + userId));

		existingUser.setFirstName(
				existingUser.getFirstName() != null ? request.getFirstName() : existingUser.getFirstName());
		existingUser.setLastName(request.getLastName() != null ? request.getLastName() : existingUser.getLastName());
		existingUser.setPhoneNo(request.getPhoneNo() != null ? request.getPhoneNo() : existingUser.getPhoneNo());
		existingUser.setGender(request.getGender() != null ? request.getGender() : existingUser.getGender());
		existingUser.setUserType(request.getUserType() != null ? request.getUserType() : existingUser.getUserType());

		existingUser.setSpecilization(
				existingUser.getSpecilization() != null ? request.getSpecilization() : existingUser.getSpecilization());

		return this.userRepository.save(existingUser);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = this.userRepository.findAll();
		return allUsers;
	}

	@Override
	public User getSingleUser(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("This user is not present" + userId));
		return user;
	}
}
