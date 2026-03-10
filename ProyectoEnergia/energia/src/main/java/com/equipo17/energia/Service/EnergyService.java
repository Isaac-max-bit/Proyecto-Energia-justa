package com.equipo17.energia.Service;


import com.equipo17.energia.Model.EnergyCapacity;
import com.equipo17.energia.Model.EnergyModel;
import com.equipo17.energia.Model.EnergyPorcentaje;
import com.equipo17.energia.Repository.EnergyCapacityRepository;
import com.equipo17.energia.Repository.EnergyPorcentajeRepository;
import com.equipo17.energia.Repository.EnergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnergyService {

    @Autowired
    private EnergyRepository repository;
    
public void cargarTodo(String fileProd) {
    System.out.println("Iniciando proceso de carga...");
    
    // Ahora usamos las variables que recibimos desde BackendApplication
    cargarArchivoProduccion(fileProd);
    
    System.out.println("Carga finalizada");
    }

    public List<Map<String, Object>> getProduction(int year) {
        List<Object[]> data = repository.findProductionBySourceAndYear(year);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : data) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("region", row[0]);
            map.put("solar_twh", row[1]);
            map.put("wind_twh", row[2]);
            map.put("hydro_twh", row[3]);
            map.put("other_renewables_twh", row[4]);
            result.add(map);
        }
        return result;
    }

     public List<Map<String, Object>> getTop10Wind(int year) {
        List<Object[]> data = repository.findTop10WindByYear(year);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : data) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("pais", row[0]);
            map.put("produccion_eolica_twh", row[1]);
            result.add(map);
        }
        return result;
    }

    public Map<String, Object> getGlobalParticipation(int year) {
        List<Object[]> data = repository.findGlobalParticipationByYear(year);
        Map<String, Object> map = new LinkedHashMap<>();
        if (!data.isEmpty()) {
            Object[] row = data.get(0);
            map.put("total_solar_mundial", row[0]);
            map.put("total_eolica_mundial", row[1]);
            map.put("total_hidro_mundial", row[2]);
            map.put("total_otras_renovables_mundial", row[3]);
        }
        return map;
    }

    private void cargarArchivoProduccion(String nombreArchivo) {
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

                String[] datos = linea.split(",",-1);
                if (datos.length >= 4) {
                    EnergyModel model = new EnergyModel();
                    model.setEntity(datos[0].trim());
                    model.setCode(datos[1].trim());
                    model.setDataYear(Integer.parseInt(datos[2].trim()));
                    model.setWindTwh(parseDoubleSafe(datos[3]));
                    model.setHydroTwh(parseDoubleSafe(datos[4]));
                    model.setSolarTwh(parseDoubleSafe(datos[5]));
                    model.setOtherRenewablesTwh(parseDoubleSafe(datos[6]));
                           
                    repository.save(model);
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