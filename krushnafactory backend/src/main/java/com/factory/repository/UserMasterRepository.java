package com.factory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.entity.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {

    Optional<UserMaster> findByUsername(String username);

    boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}