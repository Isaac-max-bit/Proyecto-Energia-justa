package com.equipo17.energia.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.equipo17.energia.Model.Country;
import com.equipo17.energia.Repository.CountryRepository;
import com.equipo17.energia.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Country save(Country country) {

        if (countryRepository.existsByName(country.getName())) {
            throw new ResourceNotFoundException("el país ya existe");
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