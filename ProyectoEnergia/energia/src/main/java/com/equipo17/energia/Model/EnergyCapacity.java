package com.equipo17.energia.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "energy_capacity")
public class EnergyCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity")
    private String entity;

    @Column(name = "code")
    private String code;

    @Column(name = "data_year")
    private Integer dataYear;

    @Column(name = "solar_capacity_gw")
    private Double solarCapacityGw;

    public EnergyCapacity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDataYear() {
        return dataYear;
    }

    public void setDataYear(Integer dataYear) {
        this.dataYear = dataYear;
    }

    public Double getSolarCapacityGw() {
        return solarCapacityGw;
    }

    public void setSolarCapacityGw(Double solarCapacityGw) {
        this.solarCapacityGw = solarCapacityGw;
    }

}
