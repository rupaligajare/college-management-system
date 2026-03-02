package com.factory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factory.dto.UserLoginRequest;
import com.factory.dto.UserRegisterRequest;
import com.factory.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private  AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(
	        @RequestBody UserRegisterRequest request) {

	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(authService.userRegister(request));
	}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = authService.userLogin(userLoginRequest);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
