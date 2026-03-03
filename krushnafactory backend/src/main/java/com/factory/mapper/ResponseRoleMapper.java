package com.factory.mapper;

import org.springframework.stereotype.Component;

import com.factory.dto.ResponseRoleDto;
import com.factory.entity.RoleMaster;

@Component
public class ResponseRoleMapper {

	public ResponseRoleDto toRessponseRoleDto(RoleMaster roleMaster) {
		
		ResponseRoleDto roleResponseDto = new ResponseRoleDto();
		roleResponseDto.setRoleId(roleMaster.getRoleId());
		roleResponseDto.setRoleName(roleMaster.getRoleName());

        return roleResponseDto;
	}
}
