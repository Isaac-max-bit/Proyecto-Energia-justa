package com.equipo17.energia.Service;

import com.equipo17.energia.Model.RenewableEnergy;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
public class RenewableEnergyService {

    public List<RenewableEnergy> getRenewableDataFromCsv() {
        String fileName = "/03 modern-renewable-prod.csv";

        try {
            var inputStream = getClass().getResourceAsStream(fileName);
            if (inputStream == null) return List.of();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                return new CsvToBeanBuilder<RenewableEnergy>(reader)
                        .withType(RenewableEnergy.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSeparator(',') 
                        .build()
                        .parse();
            }
        } catch (Exception e) {
            System.err.println("Error procesando CSV: " + e.getMessage());
            return List.of();
        }
    }
}