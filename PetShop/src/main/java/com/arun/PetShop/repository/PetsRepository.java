package com.arun.PetShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.PetShop.model.Pets;

public interface PetsRepository extends JpaRepository<Pets, Long> {

}
