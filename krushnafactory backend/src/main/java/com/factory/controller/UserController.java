package com.factory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factory.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	  @GetMapping("/user-list")
	    public ResponseEntity<Map<String, Object>> getUserList() {

	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(userService.getUserList());
	    }
}
