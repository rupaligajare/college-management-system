package com.factory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class UserRole {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userRoleId;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private UserMaster user;

	    @ManyToOne
	    @JoinColumn(name = "role_id", nullable = false)
	    private RoleMaster role;
	
	
	

}
