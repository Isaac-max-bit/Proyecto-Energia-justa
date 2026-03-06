package com.equipo17.energia.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.equipo17.energia.Model.Country;
import com.equipo17.energia.Repository.CountryRepository;
import com.equipo17.energia.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Country save(Country country) {

        if (countryRepository.existsByName(country.getName())) {
            throw new ResourceNotFoundException("El país ya existe");
        }

        return countryRepository.save(country);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("País no encontrado"));
    }
}


//import com.equipo17.energia.exception.DuplicateResourceException;

/* @Service
@RequiredArgsConstructor
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    // =============================
    // CREATE
    // =============================
    public Country save(Country country) {

        if (countryRepository.existsByNameIgnoreCase(country.getName())) {
            throw new DuplicateResourceException("El país ya existe");
        }

        return countryRepository.save(country);
    }

    // =============================
    // READ ALL
    // =============================
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    // =============================
    // READ BY ID
    // =============================
    @Transactional(readOnly = true)
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("País no encontrado con id: " + id));
    }

    // =============================
    // UPDATE
    // =============================
    public Country update(Long id, Country updatedCountry) {

        Country existingCountry = findById(id);

        if (!existingCountry.getName().equalsIgnoreCase(updatedCountry.getName())
                && countryRepository.existsByNameIgnoreCase(updatedCountry.getName())) {

            throw new DuplicateResourceException("Ya existe un país con ese nombre");
        }

        existingCountry.setName(updatedCountry.getName());
        existingCountry.setPopulation(updatedCountry.getPopulation());
        existingCountry.setRenewableEnergyProduction(updatedCountry.getRenewableEnergyProduction());
        existingCountry.setFossilEnergyProduction(updatedCountry.getFossilEnergyProduction());
        existingCountry.setCarbonEmissions(updatedCountry.getCarbonEmissions());

        return countryRepository.save(existingCountry);
    }

    // =============================
    // DELETE
    // =============================
    public void delete(Long id) {

        Country country = findById(id);

        countryRepository.delete(country);
    }
} */