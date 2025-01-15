package com.arun.PetShop.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.arun.PetShop.model.Photo;

public interface PhotoService {
	Photo savePhoto(MultipartFile file,Long userId) throws IOException, SQLException;
	Optional<Photo> getPhotoById(Long id);
	void deletePhoto(Long id,Long UserID);
	Photo updatePhoto(Long id, MultipartFile file) throws SQLException, IOException;
	byte[] getImageData(Long id) throws SQLException;

}
