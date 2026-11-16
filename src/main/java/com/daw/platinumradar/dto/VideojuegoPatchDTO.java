package com.daw.platinumradar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Objeto de transferencia de datos (DTO) para la actualización parcial (PATCH) de un videojuego. Todos los campos son opcionales para permitir modificar solo atributos específicos.")
public class VideojuegoPatchDTO {

    @Size(min = 2, max = 100, message = "El titulo debe tener entre 2 y 100 caracteres.")
    @Schema(description = "Nuevo título del videojuego.", example = "Elden Ring: Shadow of the Erdtree")
    private String titulo;

    @Pattern(regexp = "^[0-9]+$", message = "El steamAppId solo permite números")
    @Schema(description = "Nuevo identificador numérico oficial de Steam.", example = "2778580")
    private String steamAppId;

    @Schema(description = "Nuevo estado en la biblioteca.", example = "Completado")
    private String estado;

    @PositiveOrZero(message = "horasJugadas debe ser mayor o igual a cero")
    @Schema(description = "Horas jugadas actualizadas.", example = "120.5")
    private Double horasJugadas;

    @Min(value = 0, message = "")
    @Max(value = 100, message = "")
    @Schema(description = "Progreso actualizado de la historia.", example = "100.0")
    private Double porcentajeHistoria;

    @PositiveOrZero(message = "el precio debe ser positivo")
    @Schema(description = "Nuevo precio objetivo esperado en euros.", example = "15.00")
    private Double precioObjetivo;

    @PastOrPresent(message = "la ultima partida solo puede ser registrada en una fecha anterior o el presente")
    @Schema(description = "Fecha actualizada de la última vez que se jugó.", example = "2026-11-16")
    private LocalDate ultimaPartida;

    @Email(message = "el email tiene que tener formato email")
    @Schema(description = "Nuevo correo electrónico del usuario.", example = "nuevo_email@daw.com")
    private String emailUsuario;

    @Schema(description = "Cambio en la preferencia de alertas de ofertas.", example = "false")
    private Boolean alertaOfertaActiva;

    @Schema(description = "Lista actualizada de logros desbloqueados.", example = "[\"First Blood\", \"Godrick Defeated\", \"Elden Lord\"]")
    private List<String> logrosDesbloqueados = new ArrayList<>();
}