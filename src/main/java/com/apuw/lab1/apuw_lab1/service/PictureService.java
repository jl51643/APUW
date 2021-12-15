package com.apuw.lab1.apuw_lab1.service;

import com.apuw.lab1.apuw_lab1.domain.Picture;

import java.util.List;

public interface PictureService {

	Picture savePicture(Picture picture);

	Picture getPicture(Long id);

	Picture updatePicture(Picture picture);

	boolean deletePicture(Long id);

	List<Picture> getPictures();
}
