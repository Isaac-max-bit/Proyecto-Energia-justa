package com.equipo17.energia.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.equipo17.energia.Model.Country;
import com.equipo17.energia.Repository.CountryRepository;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){
        this.countryRepository=countryRepository;
    }

    public Country crearCountry(Country country){
        return countryRepository.save(country);
    }

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Optional<Country> findById(Long id){
        return countryRepository.findById(id);
    }

    public Country update(Long id, Country countryDetails){
        Country country=countryRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"País no encontrado"));

        if(countryDetails.getName()!=null && countryDetails.getName().trim().isEmpty()){
            country.setName(countryDetails.getName());
        }

        return countryRepository.save(country);
    }
}
