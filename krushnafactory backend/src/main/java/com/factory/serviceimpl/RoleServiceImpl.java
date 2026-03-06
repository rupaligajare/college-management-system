package com.factory.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factory.dto.ResponseRoleDto;
import com.factory.entity.RoleMaster;
import com.factory.exception.BadRequestException;
import com.factory.exception.DuplicateResourceException;
import com.factory.exception.ResourceNotFoundException;
import com.factory.mapper.ResponseRoleMapper;
import com.factory.repository.RoleMasterRepository;
import com.factory.service.RoleService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMasterRepository roleMasterRepository;
	
	@Autowired
	private ResponseRoleMapper responseRoleMapper;

	@Override
	public Map<String, Object> createRole(HttpServletRequest request,String role) {

	    if (role == null || role.trim().isEmpty()) {
	        throw new BadRequestException("Role name cannot be empty");
	    }

	    // 🔹 Check if role already exists
	    boolean exists = roleMasterRepository.existsByRoleNameIgnoreCase(role.trim());

	    if (exists) {
	        throw new BadRequestException("Role '" + role + "' already exists");
	    }

	    try {
	        RoleMaster roleMaster = new RoleMaster();
	        roleMaster.setCreatedBy((Long)request.getAttribute("userId"));
	        String roleName=role.trim().toUpperCase();
	        roleMaster.setRoleName(roleName);

	        RoleMaster savedRole = roleMasterRepository.save(roleMaster);

	        return Map.of(
	                "Success", true,
	                "Message", "Role " + savedRole.getRoleName() + " created successfully!"
	        );

	    } catch (Exception e) {
	        throw new BadRequestException("Failed to create role. Please try again.");
	    }
	}

	@Override
	public Map<String, Object> getRoleList() {

	    List<RoleMaster> roleList = roleMasterRepository.findAll();
	    
	    if (roleList.isEmpty()) {
	        throw new ResourceNotFoundException("No roles found");
	    }
	    
	    List<ResponseRoleDto> roleDtoList=new ArrayList<>();
	    
	    for(RoleMaster role:roleList) {
	    	roleDtoList.add(responseRoleMapper.toResponseRoleDto(role));
	    }
	   
	   

	    return Map.of(
	            "status", "success",
	            "data", roleDtoList
	    );
	}

	@Override
	public Map<String, Object> getRole(Long roleId) {
		
		RoleMaster role=roleMasterRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("No Role Found With Id "+roleId));
		
		
		
		return Map.of(
				"status","succes", 
				"data",responseRoleMapper.toResponseRoleDto(role));
	}

	@Override
	public Map<String, Object> updateRole(HttpServletRequest request, Long roleId, String roleName) {
		
		boolean isAlreadyExists = roleMasterRepository
	            .existsByRoleNameIgnoreCaseAndRoleIdNot(roleName, roleId);
		
		if(isAlreadyExists) {
			throw new DuplicateResourceException("Role name already exists");
		}
		RoleMaster role=roleMasterRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("No Role Found With Id "+roleId));
		
		String roleNameRef=roleName.trim().toUpperCase();
		role.setRoleName(roleNameRef);
		role.setUpdatedBy((Long)request.getAttribute("userId"));
		role.setUpdatedDate(LocalDateTime.now());
		
		RoleMaster savedRole = roleMasterRepository.save(role);
		
		return Map.of(
				"status","success",
				"Message","Role name changed to "+savedRole.getRoleName());
	}
}
