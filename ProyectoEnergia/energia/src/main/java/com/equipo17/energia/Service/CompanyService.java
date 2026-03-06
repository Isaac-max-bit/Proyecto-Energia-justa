/* package com.equipo17.energia.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.equipo17.energia.Repository.CountryRepository;
import com.equipo17.energia.Repository.CompanyRepository;
import com.equipo17.energia.Model.Country;
import com.equipo17.energia.Model.Company;
import com.equipo17.energia.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    public Company save(Company company) {

        Long countryId = company.getCountry().getId();

        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("País no encontrado"));

        if (companyRepository.existsByNameAndCountryId(company.getName(), countryId)) {
            throw new ResourceNotFoundException("La compañia ya existe en este país");
        }

        company.setCountry(country);

        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("compañia no encontrada"));
    }

    public List<Company> findByCountry(Long countryId) {
        return companyRepository.findByCountryId(countryId);
    }
} */