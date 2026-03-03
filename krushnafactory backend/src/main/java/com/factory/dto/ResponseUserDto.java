package com.factory.dto;

import com.factory.enumset.LoginPermissionEnum;

import lombok.Data;

@Data
public class ResponseUserDto {
	
    private Long userId;
	private String fullName;
    private String username;
    private String email;  
    private String contact;
    private String address;
    private LoginPermissionEnum loginPermissionEnum;
}
