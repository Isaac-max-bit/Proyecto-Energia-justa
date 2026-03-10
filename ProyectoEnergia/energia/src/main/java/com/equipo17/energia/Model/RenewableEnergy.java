package com.equipo17.energia.Model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;

@Entity
@Table(name = "renewable_energy")
public class RenewableEnergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "Entity")
    private String entity;

    @CsvBindByName(column = "Code")
    private String code;

    @CsvBindByName(column = "Year")
    private int year;

    // Nombres de las columnas en el CSV
    @CsvBindByName(column = "Electricity from wind (TWh)")
    private Double windProduction;

    @CsvBindByName(column = "Electricity from hydro (TWh)")
    private Double hydroProduction;

    @CsvBindByName(column = "Electricity from solar (TWh)")
    private Double solarProduction;

    public RenewableEnergy() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public Double getWindProduction() { return windProduction; }
    public void setWindProduction(Double windProduction) { this.windProduction = windProduction; }
    public Double getSolarProduction() { return solarProduction; }
    public void setSolarProduction(Double solarProduction) { this.solarProduction = solarProduction; }
    public Double getHydroProduction() { return hydroProduction; }
    public void setHydroProduction(Double hydroProduction) { this.hydroProduction = hydroProduction; }
}