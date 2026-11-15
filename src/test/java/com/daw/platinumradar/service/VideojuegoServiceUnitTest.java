package com.daw.platinumradar.service;

import com.daw.platinumradar.dto.VideojuegoResumenDTO;
import com.daw.platinumradar.exception.RecursoNoEncontradoException;
import com.daw.platinumradar.model.Videojuego;
import com.daw.platinumradar.repository.VideojuegoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideojuegoServiceUnitTest {

    @Mock
    private VideojuegoRepository videojuegoRepository;

    @InjectMocks
    private VideojuegoService videojuegoService;

    @Test
    void obtenerPorCodigo_DebeDevolverDto_SiExiste() {
        Videojuego juego = new Videojuego();
        juego.setCodigoRegistro("uuid-123");
        juego.setTitulo("Dark Souls");

        when(videojuegoRepository.findByCodigoRegistro("uuid-123")).thenReturn(Optional.of(juego));

        VideojuegoResumenDTO resultado = videojuegoService.obtenerPorCodigo("uuid-123");

        assertThat(resultado.getTitulo()).isEqualTo("Dark Souls");
    }

    @Test
    void obtenerPorCodigo_DebeLanzarExcepcion_SiNoExiste() {
        when(videojuegoRepository.findByCodigoRegistro("fake-uuid")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> videojuegoService.obtenerPorCodigo("fake-uuid"))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessageContaining("No se ha encontrado");
    }
}