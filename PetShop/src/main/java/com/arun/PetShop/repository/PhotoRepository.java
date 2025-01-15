package com.arun.PetShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.PetShop.model.Photo;

public interface PhotoRepository  extends JpaRepository<Photo, Long>{

}
