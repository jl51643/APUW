package com.apuw.lab1.apuw_lab1.api;

import com.apuw.lab1.apuw_lab1.domain.Role;
import com.apuw.lab1.apuw_lab1.domain.User;
import com.apuw.lab1.apuw_lab1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userService.saveUser(user);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users" + newUser.getId()).toUriString());
		return ResponseEntity.created(uri).body(newUser);
	}

	@PostMapping("/roles")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		Role newRole = userService.saveRole(role);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles" + newRole.getId()).toUriString());
		return ResponseEntity.created(uri).body(newRole);
	}

	@PostMapping("users/{username}/roles")
	public ResponseEntity<?> addRoleToUser (@RequestBody String roleName, @PathVariable("username") String username) {
		userService.addRoleToUser(username, roleName);
		return ResponseEntity.ok().build();
	}
}
