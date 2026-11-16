package com.daw.platinumradar.exception;

import com.daw.platinumradar.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.daw.platinumradar.controller")
public class ManejadorExcepcionesGlobal {

    // 1. Captura de excepciones de validación de los DTOs (@Valid) - HTTP 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });

        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error en la validación de los datos enviados",
                errores
        );
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // 2. Captura de violaciones de unicidad en la base de datos (ej. UNIQUE) - HTTP 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> manejarViolacionDeIntegridad(DataIntegrityViolationException ex) {
        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflicto en la base de datos: el registro ya existe o viola una restricción de unicidad.",
                null
        );
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    // 3. Captura genérica de imprevistos incontrolados - HTTP 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarExcepcionGenerica(Exception ex) {
        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno inesperado del servidor: " + ex.getMessage(),
                null
        );
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 4. Captura de Recurso No Encontrado - HTTP 404
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // 5. Captura de Excepción de Regla de Negocio - HTTP 422
    @ExceptionHandler(VideojuegoDuplicadoException.class)
    public ResponseEntity<ErrorResponse> manejarReglaDeNegocio(VideojuegoDuplicadoException ex) {
        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(respuesta, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}