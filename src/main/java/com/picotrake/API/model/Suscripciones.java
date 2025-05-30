package com.picotrake.API.model;

import com.picotrake.API.enums.TipoSuscripcion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suscripciones")
public class Suscripciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_suscripciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSuscripcion tipo;

    @Column(nullable = false)
    private float precio;
}
