package com.daw.platinumradar.repository;

import com.daw.platinumradar.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    Optional<Videojuego> findByCodigoRegistro(String codigoRegistro);

    List<Videojuego> findByEstadoIgnoreCase(String estado);
}