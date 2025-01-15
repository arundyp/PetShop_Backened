package com.arun.PetShop.serviceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arun.PetShop.exception.ResourceNotFoundException;
import com.arun.PetShop.model.Photo;
import com.arun.PetShop.model.User;
import com.arun.PetShop.repository.PhotoRepository;
import com.arun.PetShop.repository.UserRepository;
import com.arun.PetShop.service.PhotoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

	private final PhotoRepository photoRepository;
	private final UserRepository userRepository;

	@Override
	public Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException {
		Optional<User> user = this.userRepository.findById(userId);
		System.out.println("****user*****" + user);
		Photo photo = new Photo();
		if (file != null && !file.isEmpty()) {
			byte[] photoBytes = file.getBytes();
			photo.setImage(photoBytes); // Use byte[] instead of Blob
			photo.setFileName(file.getOriginalFilename());
			photo.setFileType(file.getContentType());
		}
		Photo savedPhoto = this.photoRepository.save(photo);
		user.ifPresent(theUser -> theUser.setPhoto(savedPhoto));
		userRepository.save(user.get());
		return savedPhoto;
	}

	@Override
	public Optional<Photo> getPhotoById(Long id) {
		Optional<Photo> orElseThrow = Optional.of(this.photoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Photo is not available")));
		return orElseThrow;
	}

	@Transactional
	@Override
	public void deletePhoto(Long id, Long userId) {
		this.userRepository.findById(userId).ifPresentOrElse(User::removeUserPhoto, () -> {

			throw new ResourceNotFoundException("Not Found");
		});

		photoRepository.findById(id).ifPresentOrElse(photoRepository::delete, () -> {
			throw new ResourceNotFoundException("Not Found");
		});

	}

	@Override
	public Photo updatePhoto(Long id, MultipartFile file) throws SQLException, IOException {

		Photo photo = getPhotoById(id).get();
		if (photo != null) {
			byte[] photoBytes = file.getBytes();
			photo.setImage(photoBytes); // Use byte[] instead of Blob
			photo.setFileName(file.getOriginalFilename());
			photo.setFileType(file.getContentType());
			return this.photoRepository.save(photo);
		}
		throw new ResourceNotFoundException("cant be updated");
	}

	@Override
	public byte[] getImageData(Long id) throws SQLException {
		Optional<Photo> photoById = getPhotoById(id);
		if (photoById.isPresent()) {

			byte[] image = photoById.get().getImage();
			int length = image.length;
			return new byte[length];

		}

		return null;
	}

}
