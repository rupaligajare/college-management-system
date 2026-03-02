package com.factory.entity;

import com.factory.auditable.Auditable;
import com.factory.enumset.LoginPermissionEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "user_master")
public class UserMaster extends Auditable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

	@Column(nullable = false)
	private String fullName;
	
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(unique = true)
    private String email;

    
    private String contact;

    private String address;
    
    @Enumerated(EnumType.STRING)
    private LoginPermissionEnum loginPermissionEnum = LoginPermissionEnum.PENDING;
}
