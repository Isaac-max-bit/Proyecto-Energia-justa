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

    /**
     * Lee el archivo CSV desde la carpeta de recursos y lo convierte
     * en una lista de objetos RenewableEnergy.
     */
    public List<RenewableEnergy> getRenewableDataFromCsv() {
        // El nombre debe coincidir EXACTAMENTE con el archivo en src/main/resources
        String fileName = "/03 modern-renewable-prod.csv";

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getResourceAsStream(fileName)), 
                        StandardCharsets.UTF_8))) {

            return new CsvToBeanBuilder<RenewableEnergy>(reader)
                    .withType(RenewableEnergy.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

        } catch (Exception e) {
            // Log del error para depuración
            System.err.println("Error procesando el CSV: " + e.getMessage());
            return List.of(); // Devuelve lista vacía en caso de fallo
        }
    }
}
