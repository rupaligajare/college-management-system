package com.factory.service;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import jakarta.servlet.http.HttpServletRequest;

public interface RoleService {

	public Map<String, Object> createRole(HttpServletRequest request,String role);

	public Map<String,Object> getRoleList();

	public Map<String,Object> getRole(Long roleId);

	public Map<String,Object> updateRole(HttpServletRequest request,Long roleId, String roleName);
}
