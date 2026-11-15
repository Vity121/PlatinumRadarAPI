package com.daw.platinumradar.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Utiliza el application-test.properties que creamos
class VideojuegoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearVideojuego_ConDatosValidos_DebeGuardarYDevolver201() throws Exception {
        String jsonPeticion = """
                {
                  "titulo": "Hollow Knight",
                  "steamAppId": "367520",
                  "estado": "Jugando",
                  "horasJugadas": 10.5,
                  "porcentajeHistoria": 15.0,
                  "precioObjetivo": 14.99,
                  "ultimaPartida": "2024-01-01",
                  "emailUsuario": "test@daw.com",
                  "alertaOfertaActiva": true
                }
                """;

        mockMvc.perform(post("/api/videojuegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPeticion))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Hollow Knight"))
                .andExpect(jsonPath("$.codigoRegistro").exists());
    }

    @Test
    void crearVideojuego_ConDatosInvalidos_DebeDevolver400() throws Exception {
        String jsonInvalido = """
                {
                  "titulo": "",
                  "horasJugadas": -5.0,
                  "emailUsuario": "correo-falso"
                }
                """;

        mockMvc.perform(post("/api/videojuegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erroresCampo.titulo").exists())
                .andExpect(jsonPath("$.erroresCampo.horasJugadas").exists())
                .andExpect(jsonPath("$.erroresCampo.emailUsuario").exists());
    }
}