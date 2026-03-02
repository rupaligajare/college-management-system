package com.factory.service;

import java.util.Map;

import org.jspecify.annotations.Nullable;

import com.factory.dto.UserLoginRequest;
import com.factory.dto.UserRegisterRequest;

public interface AuthService {

	public Map<String, Object> userRegister(UserRegisterRequest userRegisterRequest);

	String userLogin(UserLoginRequest userLoginRequest);

}
