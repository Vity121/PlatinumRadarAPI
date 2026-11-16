package com.daw.platinumradar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos (DTO) utilizado para la creación de un nuevo videojuego en el sistema PlatinumRadar.")
public class VideojuegoCreacionDTO {

    @NotBlank(message = "El titulo no puede estar vacío.")
    @Size(min = 2, max = 100, message = "El titulo debe tener entre 2 y 100 caracteres.")
    @Schema(description = "Título oficial del videojuego.", example = "Elden Ring")
    private String titulo;

    @NotBlank(message = "El steamApId no puede estar vacío.")
    @Pattern(regexp = "^[0-9]+$", message = "El steamAppId solo permite números")
    @Schema(description = "Identificador numérico oficial del juego en la tienda de Steam.", example = "1245620")
    private String steamAppId;

    @NotNull(message = "El estado no puede ser nulo")
    @Schema(description = "Estado actual del videojuego en la biblioteca del usuario.", example = "Jugando")
    private String estado;

    @NotNull(message = "horasJugadas no puede ser nulo")
    @PositiveOrZero(message = "horasJugadas debe ser mayor o igual a cero")
    @Schema(description = "Número total de horas que el usuario ha invertido en el juego.", example = "45.5")
    private Double horasJugadas;

    @NotNull(message = "El porcentaje no debe ser nulo")
    @Min(value = 0, message = "")
    @Max(value = 100, message = "")
    @Schema(description = "Porcentaje completado de la historia o campaña principal (0 a 100).", example = "60.0")
    private Double porcentajeHistoria;

    @NotNull(message = "El precio no puede ser nulo")
    @PositiveOrZero(message = "el precio debe ser positivo")
    @Schema(description = "Precio máximo en euros que el usuario está dispuesto a pagar en unas futuras rebajas.", example = "19.99")
    private Double precioObjetivo;

    @NotNull(message = "ultima partida no puede ser null")
    @PastOrPresent(message = "la ultima partida solo puede ser registrada en una fecha anterior o el presente")
    @Schema(description = "Fecha en la que el usuario jugó por última vez.", example = "2026-11-16")
    private LocalDate ultimaPartida;

    @NotBlank(message = "el email no puede estar en blanco")
    @Email(message = "el email tiene que tener formato email")
    @Schema(description = "Correo electrónico del usuario propietario de este registro.", example = "alumno@daw.com")
    private String emailUsuario;

    @AssertTrue(message = "Debes confirmar que eres mayor de edad o tienes permiso para registrar este juego")
    @Schema(description = "Casilla de verificación legal requerida para el registro del juego.", example = "true")
    private Boolean mayorEdad;

    @NotNull(message = "alertaOferta no puede ser nulo")
    @Schema(description = "Indica si el usuario desea recibir una notificación cuando el juego baje del precio objetivo.", example = "true")
    private Boolean alertaOfertaActiva;

    @Schema(description = "Lista opcional de nombres de logros desbloqueados por el usuario.", example = "[\"First Blood\", \"Godrick Defeated\"]")
    private List<String> logrosDesbloqueados = new ArrayList<>();
}