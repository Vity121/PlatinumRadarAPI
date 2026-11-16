package com.daw.platinumradar.config;

import com.daw.platinumradar.model.Videojuego;
import com.daw.platinumradar.repository.VideojuegoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Configuration
@Profile("dev") // Solo se ejecuta si estamos en el perfil de desarrollo
public class DevDataInitializer implements CommandLineRunner {

    private final VideojuegoRepository videojuegoRepository;

    public DevDataInitializer(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo insertamos si la base de datos está vacía
        if (videojuegoRepository.count() == 0) {
            Videojuego juego1 = new Videojuego(null, UUID.randomUUID().toString(), "Elden Ring", "1245620",
                    "Jugando", 45.5, 60.0, 0.0, LocalDate.now(), "alumno@daw.com", false, List.of("First Blood"));

            Videojuego juego2 = new Videojuego(null, UUID.randomUUID().toString(), "Stardew Valley", "413150",
                    "Pendiente", 0.0, 0.0, 9.99, LocalDate.now(), "alumno@daw.com", true, List.of());

            videojuegoRepository.saveAll(List.of(juego1, juego2));
            System.out.println("Base de datos (H2) inicializada con datos de prueba.");
        }
    }
}