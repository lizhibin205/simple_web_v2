package com.bytrees.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.web.utils.ResponseJson;

@RestController
public class HealthCheckController {
    @RequestMapping(method=RequestMethod.GET, value="/healthCheck")
    public ResponseEntity<ResponseJson<String>> index() {
    	return new ResponseEntity<>(new ResponseJson<String>(200, "sucess.", null), HttpStatus.OK);
    }
}
