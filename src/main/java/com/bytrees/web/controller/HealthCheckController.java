package com.bytrees.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
	@RequestMapping(method=RequestMethod.GET, value="/healthCheck")
    public String index() {
    	return "ok";
    }
}
