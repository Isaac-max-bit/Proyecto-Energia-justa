package com.equipo17.energia.Model;

import com.opencsv.bean.CsvBindByName;

public class RenewableEnergy {

    @CsvBindByName(column = "Entity")
    private String entity;

    @CsvBindByName(column = "Code")
    private String code;

    @CsvBindByName(column = "Year")
    private int year;

    // Usamos Double para permitir valores nulos si el CSV tiene celdas vacías
    @CsvBindByName(column = "Wind Generation - TWh")
    private Double windProduction;

    @CsvBindByName(column = "Solar Generation - TWh")
    private Double solarProduction;

    @CsvBindByName(column = "Hydro Generation - TWh")
    private Double hydroProduction;

    public RenewableEnergy() {}

    // Getters y Setters
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
