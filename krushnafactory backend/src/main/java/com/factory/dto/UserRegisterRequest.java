package com.factory.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String fullName;
    private String username;
    private String password;
    private String roleName;
    private String contact;
    private String address;
    private String email;
}