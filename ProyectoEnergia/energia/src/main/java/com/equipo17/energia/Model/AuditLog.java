/* package com.equipo17.energia.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private String action;

	@Column(nullable = false)
	private LocalDateTime action_date;

	@ManyToOne(optional = false)
	@JoinColumn(name = "User_id", nullable = false)
	private User user;

	public AuditLog() {}

} */