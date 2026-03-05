package com.factory.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factory.dto.ResponseUserDto;
import com.factory.entity.UserMaster;
import com.factory.exception.ResourceNotFoundException;
import com.factory.mapper.ResponseUserMapper;
import com.factory.repository.UserMasterRepository;
import com.factory.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@Autowired
	private ResponseUserMapper responseUserMapper;

	@Override
	public Map<String, Object> getUserList() {

	    List<UserMaster> userList = userMasterRepository.findAll();
	    
	    List<ResponseUserDto> userDtoList=new ArrayList<>();
	    
	    if (userList.isEmpty()) {
	        throw new ResourceNotFoundException("No users found in the system");
	    }
	    
	    for(UserMaster user:userList) {
	    	userDtoList.add(responseUserMapper.toResponseUserDto(user));
	    }
	
	    return Map.of(
	            "status", "success",
	            "data", userDtoList
	    );
	}

}
