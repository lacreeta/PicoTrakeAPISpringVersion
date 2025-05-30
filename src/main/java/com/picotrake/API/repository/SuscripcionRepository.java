package com.picotrake.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picotrake.API.model.Suscripciones;

public interface SuscripcionRepository extends JpaRepository<Suscripciones, Integer> {
    Optional<Suscripciones> findById(int id);
}

