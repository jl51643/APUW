package com.apuw.lab1.apuw_lab1.repo;

import com.apuw.lab1.apuw_lab1.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
