package com.daw.platinumradar.DTO;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    private Map<String, String> erroresCampo;

    public ErrorResponse(int status, String mensaje, Map<String, String> erroresCampo) {
        this.status = status;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
        this.erroresCampo = erroresCampo;
    }
}