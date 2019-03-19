package com.bytrees.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.web.utils.ResponseJson;

@RestController
public class HealthCheckController {
	private final static Logger logger = LoggerFactory.getLogger(HealthCheckController.class); 
	
    @RequestMapping(method=RequestMethod.GET, value="/healthCheck")
    public ResponseEntity<ResponseJson<String>> index() {
    	try {
    		logger.info("healthCheck request.");
    		logger.info("test password for 123456: " + new BCryptPasswordEncoder().encode("123456"));
    		return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
    	} catch (Exception ex) {
    		return new ResponseEntity<>(new ResponseJson<String>(500, ex.getMessage(), null), HttpStatus.OK);
    	}
    }
}
