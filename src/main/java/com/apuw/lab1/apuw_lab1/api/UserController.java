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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@PostMapping("")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userService.saveUser(user);
		if (newUser == null) {
			return ResponseEntity.badRequest().build();
		}
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users" + newUser.getId()).toUriString());
		return ResponseEntity.created(uri).body(newUser);
	}

	@PutMapping("")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User newUser = userService.updateUser(user);
		if (newUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(newUser);
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteUser(@RequestBody User user) {
		if (userService.deleteUser(user)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		User user = userService.getUser(username);
		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(user);
		}
	}

	@PostMapping("/{username}/roles")
	public ResponseEntity<?> addRoleToUser (@RequestBody Role role, @PathVariable("username") String username) {
		userService.addRoleToUser(username, role.getName());
		return ResponseEntity.ok().build();
	}

}
