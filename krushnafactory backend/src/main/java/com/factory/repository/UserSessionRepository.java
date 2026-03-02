package com.factory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factory.entity.UserMaster;
import com.factory.entity.UserSession;
import com.factory.enumset.SessionStatusEnum;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByToken(String token);

    List<UserSession> findByStatus(SessionStatusEnum status);

	List<UserSession> findByUserAndStatus(UserMaster user, SessionStatusEnum active);
}
