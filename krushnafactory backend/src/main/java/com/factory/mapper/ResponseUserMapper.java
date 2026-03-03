package com.factory.mapper;

import org.springframework.stereotype.Component;

import com.factory.dto.ResponseUserDto;
import com.factory.entity.UserMaster;
@Component
public class ResponseUserMapper {

	public ResponseUserDto toResponseUserDto(UserMaster userMaster) {
		
		ResponseUserDto userResponseDto = new ResponseUserDto();
		
		userResponseDto.setUsername(userMaster.getUsername());
		userResponseDto.setUserId(userMaster.getUserId());
		userResponseDto.setFullName(userMaster.getFullName());
		userResponseDto.setEmail(userMaster.getEmail());
		userResponseDto.setContact(userMaster.getContact());
		userResponseDto.setAddress(userMaster.getAddress());
		userResponseDto.setLoginPermissionEnum(userMaster.getLoginPermissionEnum());
		
        return userResponseDto;
	}
}
