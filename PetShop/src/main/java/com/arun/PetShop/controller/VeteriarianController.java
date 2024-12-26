package com.arun.PetShop.controller;


import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.VeteriarianRepository;
import com.arun.PetShop.request.RegistractionRequest;
import com.arun.PetShop.service.VeteriarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VeteriarianController {
    private final VeteriarianService veteriarianService;

    @PostMapping("/vet/create")
    public User create(@RequestBody RegistractionRequest request){
       return  this.veteriarianService.add(request);
    }

}
