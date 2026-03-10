package com.equipo17.energia.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.equipo17.energia.Model.EnergyPorcentaje;
import com.equipo17.energia.Repository.EnergyPorcentajeRepository;

@Service
public class EnergyPorcentajeService {

    @Autowired
    private EnergyPorcentajeRepository repositoryPorcentaje;
    
    public void cargarTodo( String fileShare) {

    System.out.println("Iniciando proceso de carga...");

        cargarArchivoPorcentaje(fileShare);

     System.out.println("Carga finalizada");

    }

    public List<Map<String, Object>> getPercentage(int year) {
        List<Object[]> data = repositoryPorcentaje.findRenewablePercentageByYear(year);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : data) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("region", row[0]);
            map.put("porcentaje_renovable", row[1]);
            result.add(map);
        }
        return result;
    }

     private void cargarArchivoPorcentaje(String nombreArchivo) {
        try {
            // Usamos ClassPathResource pa' encontrar o leer los archicos cvs en resources
            ClassPathResource resource = new ClassPathResource(nombreArchivo);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Salta el encabezado
                }

                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    EnergyPorcentaje model = new EnergyPorcentaje();
                    model.setEntity(datos[0].trim());
                    model.setCode(datos[1].trim());
                    model.setDataYear(Integer.parseInt(datos[2].trim()));
                    
                    model.setRenewablesSharePercent(parseDoubleSafe(datos[3]));
                           
                    repositoryPorcentaje.save(model);
                }
            }
            System.out.println("Cargado con exito: " + nombreArchivo);
            reader.close();

        } catch (Exception e) {
            System.err.println("Error cargando" + nombreArchivo + ": " + e.getMessage());
        }
    }

    private Double parseDoubleSafe(String valor) {
        try {
            return (valor == null || valor.trim().isEmpty()) ? 0.0 : Double.parseDouble(valor.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }


}
