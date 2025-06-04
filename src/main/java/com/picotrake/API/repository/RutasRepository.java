package com.picotrake.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picotrake.API.model.Ruta;

public interface RutasRepository extends JpaRepository<Ruta, Long> {

    Optional<Ruta> findByNombreRuta(String nombre_ruta);
} 
