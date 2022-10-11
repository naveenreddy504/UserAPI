package com.naveen.microservices;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.naveen.microservices.entity.User;
import com.naveen.microservices.repository.UserRepository;

@SpringBootApplication
public class UsersAPIApplication {
	@Autowired
	private UserRepository userrepo;

	@PostConstruct
	public void init() {
		List<User> users = Arrays.asList(
				new User(Long.valueOf(101), "Naveen", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(102), "Naveen", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(103), "Mallareddy", "Vanakuru", "IT-98@gmail.com", Long.valueOf(102)),
				new User(Long.valueOf(104), "Gahan", "Vanakuru", "IT-98@gmail.com", Long.valueOf(102)),
				new User(Long.valueOf(105), "Aadwik", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(106), "Ramesh", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(107), "Rajesh", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(108), "Rakesh", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(109), "Mukesh", "Vanakuru", "IT-98@gmail.com", Long.valueOf(102)),
				new User(Long.valueOf(110), "Sunder", "Vanakuru", "IT-98@gmail.com", Long.valueOf(101)),
				new User(Long.valueOf(111), "Sridher", "Vanakuru", "IT-98@gmail.com", Long.valueOf(102)));
		userrepo.saveAll(users);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(UsersAPIApplication.class, args);
	}

}
