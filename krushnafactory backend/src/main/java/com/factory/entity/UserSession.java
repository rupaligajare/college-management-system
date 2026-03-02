package com.factory.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.factory.enumset.SessionStatusEnum;

@Data
@Entity
@Table(name = "user_session")
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserMaster user;

    @Column(columnDefinition = "TEXT")
    private String token;

    private LocalDateTime loginTime;

    private LocalDateTime logoutTime;

    @Enumerated(EnumType.STRING)
    private SessionStatusEnum status; // ACTIVE, LOGOUT, EXPIRED
}