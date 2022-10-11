package com.naveen.microservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.naveen.microservices.entity.User;
import com.naveen.microservices.service.UserService;
import com.naveen.microservices.vo.ResponseTemplateVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	@Autowired
	UserService service;

	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	// get all users
	@GetMapping("/all")
	public List<User> getUsers() {
		return service.getUsers();
	}

	// get User By Id
	@GetMapping("/{id}")
	public Optional<User> getUserByID(@PathVariable("id") Long userid) {
		log.info("UsersAPI Request : getUserWithDepartment using ID: " + userid);
		return service.getUser(userid);

	}

	// get User By Id and get DepartmentAPI data
	@GetMapping("/rest/{id}")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userid) {
		log.info("UsersAPI Request : getUserWithDepartment using ID: " + userid);
		return service.getUserWithDepartmentuserid(userid);

	}

	// Paginated implementation
	@GetMapping("/getUsersPageable/{pageNo}")
	public Page<User> getUsersPageable(@PathVariable("pageNo")  Long pageNo) {		 
		return service.getUsersPageable(pageNo);
	}

}
