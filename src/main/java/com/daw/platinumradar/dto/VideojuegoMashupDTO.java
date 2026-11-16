package com.daw.platinumradar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "DTO híbrido (Mashup) que combina los datos locales almacenados en la base de datos H2 con la información en tiempo real obtenida de la API pública de Steam.")
public class VideojuegoMashupDTO {

    // Datos locales
    @Schema(description = "Código único (UUID) del registro local.", example = "550e8400-e29b-41d4-a716-446655440000")
    private String codigoRegistro;

    @Schema(description = "Título guardado localmente por el usuario.", example = "Elden Ring")
    private String titulo;

    @Schema(description = "Estado local del juego.", example = "Pendiente")
    private String estado;

    @Schema(description = "Horas jugadas almacenadas en local.", example = "0.0")
    private Double horasJugadas;

    @Schema(description = "Porcentaje de historia completado (local).", example = "0.0")
    private Double porcentajeHistoria;

    @Schema(description = "Precio objetivo guardado por el usuario.", example = "29.99")
    private Double precioObjetivo;

    @Schema(description = "Fecha de la última vez que se jugó.", example = "2026-10-01")
    private LocalDate ultimaPartida;

    @Schema(description = "Logros desbloqueados localmente.", example = "[]")
    private List<String> logrosDesbloqueados;

    // Datos externos (API de Steam)
    @Schema(description = "Nombre oficial del juego según la base de datos de Steam.", example = "ELDEN RING")
    private String nombreSteamOficial;

    @Schema(description = "Precio actual formateado devuelto por la tienda de Steam.", example = "59,99€")
    private String precioActualSteam;

    @Schema(description = "Bandera booleana que indica si la conexión con la API externa fue exitosa.", example = "true")
    private Boolean exitoIntegracion;
}