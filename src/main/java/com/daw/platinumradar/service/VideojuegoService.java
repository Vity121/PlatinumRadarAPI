package com.daw.platinumradar.service;

import com.daw.platinumradar.dto.VideojuegoCreacionDTO;
import com.daw.platinumradar.dto.VideojuegoPatchDTO;
import com.daw.platinumradar.dto.VideojuegoResumenDTO;
import com.daw.platinumradar.model.Videojuego;
import com.daw.platinumradar.repository.VideojuegoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoResumenDTO crearVideojuego(VideojuegoCreacionDTO dto) {
        Videojuego juego = new Videojuego();

        juego.setTitulo(dto.getTitulo());
        juego.setSteamAppId(dto.getSteamAppId());
        juego.setEstado(dto.getEstado());
        juego.setHorasJugadas(dto.getHorasJugadas());
        juego.setPorcentajeHistoria(dto.getPorcentajeHistoria());
        juego.setPrecioObjetivo(dto.getPrecioObjetivo());
        juego.setUltimaPartida(dto.getUltimaPartida());
        juego.setEmailUsuario(dto.getEmailUsuario());
        juego.setAlertaOfertaActiva(dto.getAlertaOfertaActiva());
        juego.setLogrosDesbloqueados(dto.getLogrosDesbloqueados());

        Videojuego juegoGuardado = videojuegoRepository.save(juego);
        return mapearAResumen(juegoGuardado);
    }

    public VideojuegoResumenDTO obtenerPorCodigo(String codigoRegistro) {
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
        return mapearAResumen(juego);
    }

    public VideojuegoResumenDTO actualizarParcial(String codigoRegistro, VideojuegoPatchDTO dto) {
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));

        if (dto.getTitulo() != null) juego.setTitulo(dto.getTitulo());
        if (dto.getSteamAppId() != null) juego.setSteamAppId(dto.getSteamAppId());
        if (dto.getEstado() != null) juego.setEstado(dto.getEstado());
        if (dto.getHorasJugadas() != null) juego.setHorasJugadas(dto.getHorasJugadas());
        if (dto.getPorcentajeHistoria() != null) juego.setPorcentajeHistoria(dto.getPorcentajeHistoria());
        if (dto.getPrecioObjetivo() != null) juego.setPrecioObjetivo(dto.getPrecioObjetivo());
        if (dto.getUltimaPartida() != null) juego.setUltimaPartida(dto.getUltimaPartida());
        if (dto.getEmailUsuario() != null) juego.setEmailUsuario(dto.getEmailUsuario());
        if (dto.getAlertaOfertaActiva() != null) juego.setAlertaOfertaActiva(dto.getAlertaOfertaActiva());
        if (dto.getLogrosDesbloqueados() != null && !dto.getLogrosDesbloqueados().isEmpty()) {
            juego.setLogrosDesbloqueados(dto.getLogrosDesbloqueados());
        }

        Videojuego juegoActualizado = videojuegoRepository.save(juego);
        return mapearAResumen(juegoActualizado);
    }

    private VideojuegoResumenDTO mapearAResumen(Videojuego juego) {
        VideojuegoResumenDTO dto = new VideojuegoResumenDTO();
        dto.setCodigoRegistro(juego.getCodigoRegistro());
        dto.setTitulo(juego.getTitulo());
        dto.setSteamAppId(juego.getSteamAppId());
        dto.setEstado(juego.getEstado());
        dto.setHorasJugadas(juego.getHorasJugadas());
        dto.setPorcentajeHistoria(juego.getPorcentajeHistoria());
        dto.setPrecioObjetivo(juego.getPrecioObjetivo());
        dto.setUltimaPartida(juego.getUltimaPartida());
        dto.setAlertaOfertaActiva(juego.getAlertaOfertaActiva());
        dto.setLogrosDesbloqueados(juego.getLogrosDesbloqueados());
        return dto;
    }
}