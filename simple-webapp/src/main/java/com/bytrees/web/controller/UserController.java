package com.bytrees.web.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.service.entity.User;
import com.bytrees.service.repository.UserRepository;
import com.bytrees.web.entity.Goods;
import com.bytrees.web.utils.ResponseJson;

@RestController
public class UserController {
	/*
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method=RequestMethod.GET, value="/user/{id}")
    public ResponseEntity<ResponseJson<User>> index(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return new ResponseEntity<>(new ResponseJson<User>(404, "user id=" + id + " not found.", null)
					, HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<>(new ResponseJson<User>(200, "sucess.", user.get()), HttpStatus.OK);
    }
	*/
}
