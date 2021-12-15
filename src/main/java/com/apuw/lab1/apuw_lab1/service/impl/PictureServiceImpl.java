package com.apuw.lab1.apuw_lab1.service.impl;

import com.apuw.lab1.apuw_lab1.domain.Picture;
import com.apuw.lab1.apuw_lab1.domain.User;
import com.apuw.lab1.apuw_lab1.repo.PictureRepo;
import com.apuw.lab1.apuw_lab1.repo.UserRepo;
import com.apuw.lab1.apuw_lab1.service.PictureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PictureServiceImpl implements PictureService {

	private final PictureRepo pictureRepo;

	@Override
	public Picture savePicture(Picture picture) {
		if (pictureRepo.findByUrl(picture.getUrl()) != null) {
			return null;
		}
		log.info("Saving new picture {} to the database", picture.getName());
		return pictureRepo.save(picture);
	}

	@Override
	public Picture getPicture(Long id) {
		log.info("Fetching picture {}", id);
		Optional<Picture> picture = pictureRepo.findById(id);
		return picture.orElse(null);
	}

	@Override
	public Picture updatePicture(Picture picture) {
		Optional<Picture> oldPicture = pictureRepo.findById(picture.getId());
		if (oldPicture.isEmpty()) {
			return null;
		}

		Picture newPicture = new Picture();
		if (picture.getName() != null) {
			newPicture.setName(picture.getName());
		} else {
			newPicture.setName(oldPicture.get().getName());
		}

		newPicture.setId(oldPicture.get().getId());
		newPicture.setUrl(oldPicture.get().getUrl());
		newPicture.setUser(oldPicture.get().getUser());

		return pictureRepo.save(newPicture);
	}

	@Override
	public boolean deletePicture(Long id) {
		Optional<Picture> oldPicture = pictureRepo.findById(id);
		if (oldPicture.isEmpty()) {
			return false;
		} else {
			pictureRepo.deleteById(id);
			return true;
		}
	}

	@Override
	public List<Picture> getPictures() {
		return pictureRepo.findAll();
	}
}
