package com.daw.platinumradar.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class ExternalApiService {

    private final RestClient restClient;

    public SteamData obtenerDatosSteam(String steamAppId) {
        String url = "https://store.steampowered.com/api/appdetails?appids=" + steamAppId;

        try {
            // Consumo síncrono de la API pública
            JsonNode respuesta = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(JsonNode.class);

            // La API de Steam devuelve un JSON con el appId como clave principal
            if (respuesta != null && respuesta.has(steamAppId) && respuesta.get(steamAppId).get("success").asBoolean()) {
                JsonNode data = respuesta.get(steamAppId).get("data");
                String nombre = data.get("name").asText();
                String precio = data.has("price_overview")
                        ? data.get("price_overview").get("final_formatted").asText()
                        : "Gratis o no disponible";

                return new SteamData(nombre, precio, true);
            }
        } catch (Exception e) {
            // Tolerancia a fallos: Si no hay internet o Steam cae, no rompemos nuestra API
            System.err.println("Fallo al conectar con Steam: " + e.getMessage());
        }

        // Datos por defecto (Fallback)
        return new SteamData("Desconocido (Fallo de conexión)", "N/A", false);
    }

    // Record interno para empaquetar los 3 datos que necesitamos temporalmente
    public record SteamData(String nombre, String precio, Boolean exito) {}
}