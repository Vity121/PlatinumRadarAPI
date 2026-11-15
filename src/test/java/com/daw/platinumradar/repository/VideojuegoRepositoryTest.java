package com.daw.platinumradar.repository;


import com.daw.platinumradar.model.Videojuego;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VideojuegoRepositoryTest {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Test
    void debeEncontrarVideojuegoPorCodigoRegistro() {
        // Arrange (Preparar)
        Videojuego juego = new Videojuego(null, "uuid-test-123", "Sekiro", "814380", "Pendiente",
                0.0, 0.0, 30.0, LocalDate.now(), "test@daw.com", true, null);
        videojuegoRepository.save(juego);

        // Act (Actuar)
        Optional<Videojuego> resultado = videojuegoRepository.findByCodigoRegistro("uuid-test-123");

        // Assert (Comprobar)
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getTitulo()).isEqualTo("Sekiro");
    }
}