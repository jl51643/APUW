package com.apuw.lab1.apuw_lab1.repo;

import com.apuw.lab1.apuw_lab1.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Long> {

	Picture findByUrl(String url);
}
