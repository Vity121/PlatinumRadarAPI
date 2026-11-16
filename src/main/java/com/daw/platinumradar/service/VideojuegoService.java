package com.daw.platinumradar.service;

import com.daw.platinumradar.dto.VideojuegoCreacionDTO;
import com.daw.platinumradar.dto.VideojuegoMashupDTO;
import com.daw.platinumradar.dto.VideojuegoPatchDTO;
import com.daw.platinumradar.dto.VideojuegoResumenDTO;
import com.daw.platinumradar.exception.RecursoNoEncontradoException;
import com.daw.platinumradar.model.Videojuego;
import com.daw.platinumradar.repository.VideojuegoRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    private final ExternalApiService externalApiService;

    @CacheEvict(value = "videojuegosMashup", allEntries = true)
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

    // Obtener todos los videojuegos
    public List<VideojuegoResumenDTO> obtenerTodos() {
        return videojuegoRepository.findAll()
                .stream()
                .map(this::mapearAResumen)
                .collect(Collectors.toList());
    }

    public VideojuegoResumenDTO obtenerPorCodigo(String codigoRegistro) {
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RecursoNoEncontradoException("Videojuego no encontrado"));
        return mapearAResumen(juego);
    }

    // NUEVO: Reemplazo completo
    @CacheEvict(value = "videojuegosMashup", allEntries = true)
    public VideojuegoResumenDTO reemplazarCompleto(String codigoRegistro, VideojuegoCreacionDTO dto) {
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RecursoNoEncontradoException("Videojuego no encontrado"));

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

        Videojuego juegoActualizado = videojuegoRepository.save(juego);
        return mapearAResumen(juegoActualizado);
    }

    @CacheEvict(value = "videojuegosMashup", allEntries = true)
    public VideojuegoResumenDTO actualizarParcial(String codigoRegistro, VideojuegoPatchDTO dto) {
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RecursoNoEncontradoException("Videojuego no encontrado"));

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

    // NUEVO: Eliminar videojuego
    @CacheEvict(value = "videojuegosMashup", allEntries = true)
    public void eliminarPorCodigo(String codigoRegistro) {
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RecursoNoEncontradoException("Videojuego no encontrado"));
        videojuegoRepository.delete(juego);
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

    // Busca en base de datos, llamará a Steam y lo guardará en RAM
    @Cacheable(value = "videojuegosMashup", key = "#codigoRegistro")
    public VideojuegoMashupDTO obtenerMashupPorCodigo(String codigoRegistro) {
        // 1. Obtener de BD Local
        Videojuego juego = videojuegoRepository.findByCodigoRegistro(codigoRegistro)
                .orElseThrow(() -> new RecursoNoEncontradoException("Videojuego no encontrado"));

        // 2. Llamar a API Externa
        ExternalApiService.SteamData steamData = externalApiService.obtenerDatosSteam(juego.getSteamAppId());

        // 3. Fusionar datos (Mashup)
        VideojuegoMashupDTO mashup = new VideojuegoMashupDTO();
        mashup.setCodigoRegistro(juego.getCodigoRegistro());
        mashup.setTitulo(juego.getTitulo());
        mashup.setEstado(juego.getEstado());
        mashup.setHorasJugadas(juego.getHorasJugadas());
        mashup.setPorcentajeHistoria(juego.getPorcentajeHistoria());
        mashup.setPrecioObjetivo(juego.getPrecioObjetivo());
        mashup.setUltimaPartida(juego.getUltimaPartida());
        mashup.setLogrosDesbloqueados(juego.getLogrosDesbloqueados());

        // Asignar los datos de Steam
        mashup.setNombreSteamOficial(steamData.nombre());
        mashup.setPrecioActualSteam(steamData.precio());
        mashup.setExitoIntegracion(steamData.exito());

        return mashup;
    }
}