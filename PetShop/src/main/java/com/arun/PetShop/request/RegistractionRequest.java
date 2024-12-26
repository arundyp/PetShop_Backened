package com.arun.PetShop.request;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class RegistractionRequest {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNo;
    private String email;
    private String password;
    private String userType;
    private boolean isEnable;
    private  String specilization;
}
