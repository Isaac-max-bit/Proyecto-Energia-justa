package com.equipo17.energia.Model;

import jakarta.persistence.*;
//se necesita crear indexes
@Entity
@Table(name = "company")

public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false,unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

}