package com.equipo17.energia.Model;

import jakarta.persistence.*;

//se necesita crear indexes
@Entity
@Table(name="energy_record")
public class EnergyRecord {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column
    private Integer year;

    @Column
    private Integer month;

    @Column
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "power_plant_id", nullable = false)
	private PowerPlant power_plant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "measurement_type_id", nullable = false)
	private MeasurementType measurement_type;

    

}
