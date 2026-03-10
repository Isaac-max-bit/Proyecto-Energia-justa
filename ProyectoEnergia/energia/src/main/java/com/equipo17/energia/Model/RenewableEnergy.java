package com.equipo17.energia.Model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "renewable_energy") // Nombre de la tabla en PostgreSQL
public class RenewableEnergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Llave primaria necesaria para la base de datos

    @CsvBindByName(column = "Entity")
    private String entity;

    @CsvBindByName(column = "Code")
    private String code;

    @CsvBindByName(column = "Year")
    private int year;

    // Usamos Double para permitir valores nulos (null) que vimos en tu captura anterior
    @CsvBindByName(column = "Wind Generation - TWh")
    private Double windProduction;

    @CsvBindByName(column = "Solar Generation - TWh")
    private Double solarProduction;

    @CsvBindByName(column = "Hydro Generation - TWh")
    private Double hydroProduction;

    // Constructor vacío (Obligatorio para JPA y OpenCSV)
    public RenewableEnergy() {}

    // Getters y Setters
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
