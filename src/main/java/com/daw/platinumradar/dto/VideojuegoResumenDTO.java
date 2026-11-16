package com.daw.platinumradar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Objeto de transferencia de datos (DTO) de lectura. Devuelve la información consolidada de un videojuego al cliente.")
public class VideojuegoResumenDTO {

    @Schema(description = "Código único (UUID) público del registro en el sistema.", example = "550e8400-e29b-41d4-a716-446655440000")
    private String codigoRegistro;

    @Schema(description = "Título oficial del videojuego.", example = "Elden Ring")
    private String titulo;

    @Schema(description = "Identificador oficial en la tienda de Steam.", example = "1245620")
    private String steamAppId;

    @Schema(description = "Estado actual del videojuego.", example = "Jugando")
    private String estado;

    @Schema(description = "Número total de horas jugadas registradas.", example = "45.5")
    private Double horasJugadas;

    @Schema(description = "Porcentaje de completado de la historia principal.", example = "60.0")
    private Double PorcentajeHistoria;

    @Schema(description = "Precio máximo deseado para comprarlo en euros.", example = "19.99")
    private Double precioObjetivo;

    @Schema(description = "Fecha de la última partida.", example = "2026-11-16")
    private LocalDate ultimaPartida;

    @Schema(description = "Indicador de si la alerta de bajada de precio está activada.", example = "true")
    private Boolean alertaOfertaActiva;

    @Schema(description = "Lista de logros desbloqueados registrados localmente.", example = "[\"First Blood\", \"Godrick Defeated\"]")
    private List<String> logrosDesbloqueados = new ArrayList<>();
}