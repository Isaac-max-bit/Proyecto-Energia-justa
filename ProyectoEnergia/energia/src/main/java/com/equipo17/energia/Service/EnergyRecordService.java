package com.equipo17.energia.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.equipo17.energia.Repository.*;
import com.equipo17.energia.Model.*;
import com.equipo17.energia.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyRecordService {

    private final EnergyRecordRepository energyRecordRepository;
    private final PowerPlantRepository powerPlantRepository;
    private final MeasurementTypeRepository measurementTypeRepository;

    public EnergyRecord save(EnergyRecord record) {

        // Validar año
        if (record.getYear() == null || record.getYear() < 1900) {
            throw new ResourceNotFoundException("Año invalido");
        }

        // Validar mes
        if (record.getMonth() == null || record.getMonth() < 1 || record.getMonth() > 12) {
            throw new ResourceNotFoundException("El mes debe estar entre 1 y 12");
        }

        // Validar valor
        if (record.getValue() == null || record.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResourceNotFoundException("el valor debe ser positivo");
        }

        Long plantId = record.getPowerPlant().getId();
        Long measurementTypeId = record.getMeasurementType().getId();

        PowerPlant plant = powerPlantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Planta no encontrada"));

        MeasurementType measurementType = measurementTypeRepository.findById(measurementTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de medida no encontrado"));

        // Validar registro único
        if (energyRecordRepository
                .existsByPowerPlantIdAndYearAndMonthAndMeasurementTypeId(
                        plantId,
                        record.getYear(),
                        record.getMonth(),
                        measurementTypeId
                )) {
            throw new ResourceNotFoundException("El registro de energia ya existe en esta planta, mes y tipo");
        }

        record.setPowerPlant(plant);
        record.setMeasurementType(measurementType);

        return energyRecordRepository.save(record);
    }

    public List<EnergyRecord> findAll() {
        return energyRecordRepository.findAll();
    }

    public EnergyRecord findById(Long id) {
        return energyRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro"));
    }

    public List<EnergyRecord> findByPlant(Long plantId) {
        return energyRecordRepository.findByPowerPlantId(plantId);
    }
}