package com.picotrake.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picotrake.API.model.Montanyas;

public interface MontanyaRepository extends JpaRepository<Montanyas, Long> {

    Optional<Montanyas> findByName(String nombre_ruta);
}
