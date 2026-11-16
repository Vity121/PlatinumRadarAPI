package com.daw.platinumradar.controller;

import com.daw.platinumradar.dto.VideojuegoCreacionDTO;
import com.daw.platinumradar.dto.VideojuegoMashupDTO;
import com.daw.platinumradar.dto.VideojuegoPatchDTO;
import com.daw.platinumradar.dto.VideojuegoResumenDTO;
import com.daw.platinumradar.service.VideojuegoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@AllArgsConstructor
@Tag(name = "Videojuegos", description = "Operaciones CRUD y Mashup para la gestión de la biblioteca de videojuegos del usuario")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    @Operation(summary = "Crear un nuevo videojuego", description = "Registra un nuevo videojuego en la base de datos local.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Videojuego creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    })
    @PostMapping
    public ResponseEntity<VideojuegoResumenDTO> crearVideojuego(@Valid @RequestBody VideojuegoCreacionDTO dto) {
        VideojuegoResumenDTO nuevoJuego = videojuegoService.crearVideojuego(dto);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los videojuegos", description = "Devuelve una lista con todos los videojuegos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista recuperada con éxito")
    @GetMapping
    public ResponseEntity<List<VideojuegoResumenDTO>> obtenerTodos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodos());
    }

    @Operation(summary = "Obtener un videojuego por su código", description = "Busca un videojuego en la base de datos local usando su UUID público.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videojuego encontrado"),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún videojuego con ese código")
    })
    @GetMapping("/{codigoRegistro}")
    public ResponseEntity<VideojuegoResumenDTO> obtenerVideojuego(@PathVariable String codigoRegistro) {
        VideojuegoResumenDTO resumen = videojuegoService.obtenerPorCodigo(codigoRegistro);
        return ResponseEntity.ok(resumen);
    }

    @Operation(summary = "Actualizar parcialmente un videojuego", description = "Modifica únicamente los campos proporcionados sin afectar al resto (PATCH).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videojuego actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })
    @PatchMapping("/{codigoRegistro}")
    public ResponseEntity<VideojuegoResumenDTO> actualizarVideojuego(
            @PathVariable String codigoRegistro,
            @Valid @RequestBody VideojuegoPatchDTO dto) {
        VideojuegoResumenDTO resumen = videojuegoService.actualizarParcial(codigoRegistro, dto);
        return ResponseEntity.ok(resumen);
    }

    @Operation(summary = "Reemplazar un videojuego completo", description = "Sobrescribe todos los datos de un videojuego existente (PUT).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videojuego reemplazado con éxito"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })
    @PutMapping("/{codigoRegistro}")
    public ResponseEntity<VideojuegoResumenDTO> reemplazarVideojuego(
            @PathVariable String codigoRegistro,
            @Valid @RequestBody VideojuegoCreacionDTO dto) {
        VideojuegoResumenDTO resumen = videojuegoService.reemplazarCompleto(codigoRegistro, dto);
        return ResponseEntity.ok(resumen);
    }

    @Operation(summary = "Eliminar un videojuego", description = "Borra físicamente el videojuego de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Videojuego eliminado (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado")
    })
    @DeleteMapping("/{codigoRegistro}")
    public ResponseEntity<Void> eliminarVideojuego(@PathVariable String codigoRegistro) {
        videojuegoService.eliminarPorCodigo(codigoRegistro);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener Mashup (Local + Steam)", description = "Fusiona los datos locales del videojuego con los datos en tiempo real extraídos de la API de Steam.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos híbridos generados con éxito"),
            @ApiResponse(responseCode = "404", description = "Videojuego no encontrado en la base local")
    })
    @GetMapping("/{codigoRegistro}/steam")
    public ResponseEntity<VideojuegoMashupDTO> obtenerMashupVideojuego(@PathVariable String codigoRegistro) {
        VideojuegoMashupDTO mashup = videojuegoService.obtenerMashupPorCodigo(codigoRegistro);
        return ResponseEntity.ok(mashup);
    }
}