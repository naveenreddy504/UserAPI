package com.naveen.microservices.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.naveen.microservices.entity.User;
import com.naveen.microservices.repository.UserRepository;
import com.naveen.microservices.vo.DepartmentVO;
import com.naveen.microservices.vo.ResponseTemplateVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	UserRepository repo;
	@Autowired
	RestTemplate restTemplate;
	
	@Transactional
	public User saveUser(User user) {
		return repo.save(user);
	}
	@Transactional
	public Optional<User> getUser(Long userId) {
		return repo.findById(userId);
	}
	
	@Transactional
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	@Transactional
	public Page<User> getUsersPageable(Long pageno) {
		
		Pageable pagingSort = PageRequest.of(pageno.intValue(), 10);
		return repo.findAll(pagingSort);
	}
	
	@Transactional
	public ResponseTemplateVO getUserWithDepartmentuserid(Long userid) {
		ResponseTemplateVO vo=new ResponseTemplateVO();
		User user=repo.findById(userid).get();
		DepartmentVO department=restTemplate.getForObject("http://localhost:8081/departments/"+user.getDepartmentId(), DepartmentVO.class);
		vo.setUser(user);
		vo.setDepartment(department);
		log.info("UsersAPI Response:"+vo.toString());
		return vo;
	}

}
