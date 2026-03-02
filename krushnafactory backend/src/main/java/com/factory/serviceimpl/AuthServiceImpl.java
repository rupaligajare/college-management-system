package com.factory.serviceimpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.factory.dto.UserLoginRequest;
import com.factory.dto.UserRegisterRequest;
import com.factory.entity.RoleMaster;
import com.factory.entity.UserMaster;
import com.factory.entity.UserRole;
import com.factory.entity.UserSession;
import com.factory.enumset.LoginPermissionEnum;
import com.factory.enumset.SessionStatusEnum;
import com.factory.exception.BadRequestException;
import com.factory.exception.ForbiddenException;
import com.factory.exception.ResourceNotFoundException;
import com.factory.exception.UnauthorizedException;
import com.factory.repository.RoleMasterRepository;
import com.factory.repository.UserMasterRepository;
import com.factory.repository.UserRoleRepository;
import com.factory.repository.UserSessionRepository;
import com.factory.service.AuthService;
import com.factory.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	
	
		private final UserSessionRepository userSessionRepository;
	    private final UserMasterRepository userMasterRepository;
	    private final RoleMasterRepository roleMasterRepository;
	    private final UserRoleRepository userRoleRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final JwtUtil jwtUtil; 

	    
	    @Override
	    public Map<String, Object> userRegister(UserRegisterRequest userRegisterRequest) {

	        if (userMasterRepository.existsByUsername(userRegisterRequest.getUsername())) {
	            throw new BadRequestException("Username already exists");
	        }
	        if (userMasterRepository.existsByEmail(userRegisterRequest.getEmail())) {
	            throw new BadRequestException("Email already exists");
	        }
	        
	        RoleMaster role = roleMasterRepository
	                .findByRoleName(userRegisterRequest.getRoleName())
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Role not found"));

	        UserMaster user = new UserMaster();
	        user.setFullName(userRegisterRequest.getFullName());
	        user.setUsername(userRegisterRequest.getUsername());
	        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
	        user.setEmail(userRegisterRequest.getEmail());
	        user.setContact(userRegisterRequest.getContact());
	        user.setAddress(userRegisterRequest.getAddress());
	        user.setLoginPermissionEnum(LoginPermissionEnum.PENDING);

	        userMasterRepository.save(user);

	        

	        UserRole userRole = new UserRole();
	        userRole.setUser(user);
	        userRole.setRole(role);

	        userRoleRepository.save(userRole);

	        return Map.of(
	                "data", Map.of(
	                        "status", "success",
	                        "message", "User registered successfully. Waiting for admin approval."
	                )
	        );
	    }


	    @Override
	    public String userLogin(UserLoginRequest request) {

	        UserMaster user = userMasterRepository.findByUsername(request.getUsername())
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	            throw new UnauthorizedException("Invalid password");
	        }

	        if (user.getLoginPermissionEnum() != LoginPermissionEnum.APPROVED) {
	            throw new ForbiddenException("User not approved by admin");
	        }

	        UserRole userRole = userRoleRepository.findByUser(user)
	                .orElseThrow(() -> new ResourceNotFoundException("User role not assigned"));

	        String roleName = userRole.getRole().getRoleName();

	        List<UserSession> activeSessions =
	                userSessionRepository.findByUserAndStatus(user, SessionStatusEnum.ACTIVE);

	        for (UserSession oldSession : activeSessions) {
	            oldSession.setStatus(SessionStatusEnum.EXPIRED);
	            oldSession.setLogoutTime(LocalDateTime.now());
	        }

	        userSessionRepository.saveAll(activeSessions);

	        String token = jwtUtil.generateToken(user, roleName);

	        UserSession newSession = new UserSession();
	        newSession.setUser(user);
	        newSession.setToken(token);
	        newSession.setLoginTime(LocalDateTime.now());
	        newSession.setStatus(SessionStatusEnum.ACTIVE);

	        userSessionRepository.save(newSession);

	        return token;
	    }
	    
	    public String logout(String token) {

	        UserSession session = userSessionRepository.findByToken(token)
	                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));

	        session.setLogoutTime(LocalDateTime.now());
	        session.setStatus(SessionStatusEnum.LOGOUT);

	        userSessionRepository.save(session);

	        return "Logged out successfully";
	    }
	
}
