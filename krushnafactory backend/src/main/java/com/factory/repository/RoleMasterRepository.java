package com.factory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.entity.RoleMaster;

public interface RoleMasterRepository extends JpaRepository<RoleMaster, Long> {

    Optional<RoleMaster> findByRoleName(String roleName);

	boolean existsByRoleNameIgnoreCase(String trim);
}
