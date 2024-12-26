package com.arun.PetShop.repository;

import com.arun.PetShop.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeteriarianRepository extends JpaRepository<Veterinarian,Long> {
}
