package com.daw.platinumradar.controller;

import com.daw.platinumradar.dto.VideojuegoCreacionDTO;
import com.daw.platinumradar.dto.VideojuegoPatchDTO;
import com.daw.platinumradar.dto.VideojuegoResumenDTO;
import com.daw.platinumradar.service.VideojuegoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videojuegos")
@AllArgsConstructor
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    @PostMapping
    public ResponseEntity<VideojuegoResumenDTO> crearVideojuego(@Valid @RequestBody VideojuegoCreacionDTO dto) {
        VideojuegoResumenDTO nuevoJuego = videojuegoService.crearVideojuego(dto);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    @GetMapping("/{codigoRegistro}")
    public ResponseEntity<VideojuegoResumenDTO> obtenerVideojuego(@PathVariable String codigoRegistro) {
        VideojuegoResumenDTO resumen = videojuegoService.obtenerPorCodigo(codigoRegistro);
        return ResponseEntity.ok(resumen);
    }

    @PatchMapping("/{codigoRegistro}")
    public ResponseEntity<VideojuegoResumenDTO> actualizarVideojuego(
            @PathVariable String codigoRegistro,
            @Valid @RequestBody VideojuegoPatchDTO dto) {
        VideojuegoResumenDTO resumen = videojuegoService.actualizarParcial(codigoRegistro, dto);
        return ResponseEntity.ok(resumen);
    }
}