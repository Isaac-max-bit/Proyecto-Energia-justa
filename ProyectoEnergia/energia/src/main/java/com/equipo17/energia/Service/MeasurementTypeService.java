package com.equipo17.energia.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.MeasurementType;
import com.equipo17.energia.Repository.MeasurementTypeRepository;

@Service
public class MeasurementTypeService {
    private final MeasurementTypeRepository measurementTypeRepository;

    public MeasurementTypeService(MeasurementTypeRepository measurementTypeRepository){
        this.measurementTypeRepository=measurementTypeRepository;
    }

    public MeasurementType creaMeasurementType(MeasurementType measurementType){
        return measurementTypeRepository.save(measurementType);
    }

    public List<MeasurementType> findAll(){
        return measurementTypeRepository.findAll();
    }

    public Optional<MeasurementType> findById(Long id){
        return measurementTypeRepository.findById(id);
    }

    public MeasurementType update(Long id, MeasurementType measurementTypeDetails){
        MeasurementType measurementType=measurementTypeRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tipo de medida no encontrado"));

        if(measurementTypeDetails.getName()!=null && measurementTypeDetails.getName().trim().isEmpty()){
            measurementType.setName(measurementTypeDetails.getName());
        }

        if(measurementTypeDetails.getUnit()!=null && measurementTypeDetails.getUnit().trim().isEmpty()){
            measurementType.setUnit(measurementTypeDetails.getUnit());
        }

        return measurementTypeRepository.save(measurementType);
    }
}
