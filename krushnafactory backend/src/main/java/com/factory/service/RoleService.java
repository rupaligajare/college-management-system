package com.factory.service;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public interface RoleService {

	public Map<String, Object> createRole(HttpServletRequest request,String role);
}
