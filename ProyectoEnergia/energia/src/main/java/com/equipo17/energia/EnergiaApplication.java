package com.equipo17.energia;
import com.equipo17.energia.Service.EnergyCapacityService;
import com.equipo17.energia.Service.EnergyPorcentajeService;
import com.equipo17.energia.Service.EnergyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration; // <--- IMPORTANTE
import org.springframework.context.annotation.Bean;

// Excluimos SecurityAutoConfiguration para evitar que pida usuario/contraseña en el navegador
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EnergiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergiaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(EnergyService energyService,EnergyPorcentajeService energyPorcentajeService, EnergyCapacityService energyCapacityService ) {
        return args -> {
            try {
                System.out.println("--- Iniciando proceso de carga de datos ---");
                
                // Nombres exactos de tus archivos en src/main/resources
                String fileProd = "03-modern-renewable-prod.csv";
                String fileShare = "04-share-electricity-renewables.csv"; 
                String fileCap = "13-installed-solar-PV-capacity.csv";
                
                // Llamamos al servicio para procesar los archivos
                //comantado para que no se cargue la base de datos
                /* energyService.cargarTodo(fileProd);
                energyPorcentajeService.cargarTodo(fileShare);
                energyCapacityService.cargarTodo(fileCap); */
                
                System.out.println("--- ¡Carga de datos finalizada con éxito! ---");
            } catch (Exception e) {
                System.err.println("Error crítico durante la inicialización: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}