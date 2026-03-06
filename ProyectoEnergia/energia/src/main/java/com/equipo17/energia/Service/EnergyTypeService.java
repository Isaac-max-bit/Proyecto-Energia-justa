package com.equipo17.energia.Service;

import java.util.List;
//import java.util.Optional;

import lombok.RequiredArgsConstructor;

//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.EnergyType;
import com.equipo17.energia.Repository.EnergyTypeRepository;
import com.equipo17.energia.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class EnergyTypeService {

    private final EnergyTypeRepository energyTypeRepository;

    public EnergyType save(EnergyType energyType) {

        if (energyTypeRepository.existsByName(energyType.getName())) {
            throw new ResourceNotFoundException("El tipo de energia ya existe");
        }

        return energyTypeRepository.save(energyType);
    }

    public List<EnergyType> findAll() {
        return energyTypeRepository.findAll();
    }

    public EnergyType findById(Long id) {
        return energyTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de energia no encontrado"));
    }

    public List<EnergyType> findByRenewable(Boolean renewable) {
        return energyTypeRepository.findByRenewable(renewable);
    }
}