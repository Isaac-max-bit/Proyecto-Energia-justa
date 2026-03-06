/* package com.equipo17.energia.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.equipo17.energia.Service.CompanyService;
import com.equipo17.energia.Model.Company;
import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Company create(@RequestBody Company company) {
        return companyService.save(company);
    }

    @GetMapping
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @GetMapping("/country/{countryId}")
    public List<Company> findByCountry(@PathVariable Long countryId) {
        return companyService.findByCountry(countryId);
    }
} */