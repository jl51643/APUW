package com.apuw.lab1.apuw_lab1.repo;

import com.apuw.lab1.apuw_lab1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
