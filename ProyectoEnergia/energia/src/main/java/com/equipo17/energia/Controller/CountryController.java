package com.equipo17.energia.Controller;

import com.equipo17.energia.Service.CountryService;
import com.equipo17.energia.Model.Country;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.save(country));
    }

    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.findById(id));
    }
}

//import jakarta.validation.Valid;

/* @RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    // =============================
    // CREATE
    // =============================
    @PostMapping
    public ResponseEntity<Country> create(@Valid @RequestBody Country country) {
        Country savedCountry = countryService.save(country);

        return ResponseEntity
                .created(URI.create("/api/countries/" + savedCountry.getId()))
                .body(savedCountry);
    }

    // =============================
    // READ ALL
    // =============================
    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    // =============================
    // READ BY ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        Country country = countryService.findById(id);
        return ResponseEntity.ok(country);
    }

    // =============================
    // UPDATE
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<Country> update(
            @PathVariable Long id,
            @Valid @RequestBody Country country) {

        Country updatedCountry = countryService.update(id, country);
        return ResponseEntity.ok(updatedCountry);
    }

    // =============================
    // DELETE
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
} */