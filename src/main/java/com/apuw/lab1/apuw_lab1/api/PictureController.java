package com.apuw.lab1.apuw_lab1.api;

import com.apuw.lab1.apuw_lab1.domain.Picture;
import com.apuw.lab1.apuw_lab1.domain.User;
import com.apuw.lab1.apuw_lab1.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pictures")
public class PictureController {

	private final PictureService pictureService;

	@GetMapping("")
	public ResponseEntity<List<Picture>> getPictures() {
		return ResponseEntity.ok().body(pictureService.getPictures());
	}

	@PostMapping("")
	public ResponseEntity<Picture> savePicture(@RequestBody Picture picture) {
		Picture newPicture = pictureService.savePicture(picture);
		if (newPicture == null) {
			return ResponseEntity.badRequest().build();
		}
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pictures" + newPicture.getId()).toUriString());
		return ResponseEntity.created(uri).body(newPicture);
	}

	@PutMapping("")
	public ResponseEntity<Picture> updatePicture(@RequestBody Picture picture) {
		Picture newPicture = pictureService.updatePicture(picture);
		if (newPicture == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(newPicture);
	}

	@DeleteMapping("")
	public ResponseEntity<?> deletePicture(@RequestBody Picture picture) {
		if (pictureService.deletePicture(picture.getId())) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Picture> getPicture(@PathVariable Long id) {
		Picture picture = pictureService.getPicture(id);
		if (picture == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(picture);
		}
	}
}
