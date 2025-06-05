package com.picotrake.API.model;

import com.picotrake.API.enums.DificultadMontanya;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "montanyas")
public class Montanyas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_montanya;

    private String nombreMontanya;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DificultadMontanya dificultad;

    private Boolean acampar;
    private Boolean pernoctar;
    private Boolean especies_peligrosas;

    @Lob
    @Column(name = "geojson", columnDefinition = "TEXT")
    private String geojson;
}
