package com.arun.PetShop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fileType;
    private String fileName;
	@Lob
	private byte[] image;

}
