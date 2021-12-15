package com.apuw.lab1.apuw_lab1.service;

import com.apuw.lab1.apuw_lab1.domain.Role;
import com.apuw.lab1.apuw_lab1.domain.User;

import java.util.List;

public interface UserService {

	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String username, String roleName);

	User getUser(String username);

	List<User> getUsers();

	User updateUser(User user);

	boolean deleteUser(User user);
}
