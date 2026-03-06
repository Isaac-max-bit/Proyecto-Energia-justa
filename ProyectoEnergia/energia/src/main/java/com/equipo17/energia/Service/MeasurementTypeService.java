/* package com.equipo17.energia.Service;


import java.util.List;
//import java.util.Optional;

import lombok.RequiredArgsConstructor;

//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.MeasurementType;
import com.equipo17.energia.Repository.MeasurementTypeRepository;
import com.equipo17.energia.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class MeasurementTypeService {

    private final MeasurementTypeRepository measurementTypeRepository;

    public MeasurementType save(MeasurementType measurementType) {

        if (measurementTypeRepository.existsByName(measurementType.getName())) {
            throw new ResourceNotFoundException("El tipo de medida ya existe");
        }

        return measurementTypeRepository.save(measurementType);
    }

    public List<MeasurementType> findAll() {
        return measurementTypeRepository.findAll();
    }

    public MeasurementType findById(Long id) {
        return measurementTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de medida no encontrado"));
    }
} */