package com.daw.platinumradar.controller;

import com.daw.platinumradar.dto.VideojuegoResumenDTO;
import com.daw.platinumradar.service.VideojuegoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideojuegoController.class)
class VideojuegoControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VideojuegoService videojuegoService;

    @Test
    void obtenerVideojuego_DebeDevolver200YDatos() throws Exception {
        VideojuegoResumenDTO dto = new VideojuegoResumenDTO();
        dto.setTitulo("Bloodborne");
        dto.setCodigoRegistro("uuid-bb-1");

        when(videojuegoService.obtenerPorCodigo("uuid-bb-1")).thenReturn(dto);

        mockMvc.perform(get("/api/videojuegos/uuid-bb-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Bloodborne"));
    }
}