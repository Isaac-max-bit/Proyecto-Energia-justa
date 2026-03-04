package com.equipo17.energia.Repository;

import com.equipo17.energia.Model.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long>{
 // Buscar empresas por país
    List<Company> findByCountryId(Long countryId);

    // Validar empresa única por país
    Optional<Company> findByNameAndCountryId(String name, Long countryId);

    boolean existsByNameAndCountryId(String name, Long countryId);

}
