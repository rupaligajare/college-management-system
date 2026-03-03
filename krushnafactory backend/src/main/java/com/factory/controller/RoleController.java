package com.factory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factory.service.RoleService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
    @PostMapping("/addRole")
    public ResponseEntity<Map<String, Object>> createRole(
    		HttpServletRequest request,
    		@RequestParam String role) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleService.createRole(request,role));
    }
}
