package com.equipo17.energia.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "energy_type")
public class EnergyType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false,unique = true)
	private String name;

	@Column(nullable=false)
	private boolean renewbable;

	public EnergyType() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRenewbable() {
		return renewbable;
	}

	public void setRenewbable(boolean renewbable) {
		this.renewbable = renewbable;
	}
}
