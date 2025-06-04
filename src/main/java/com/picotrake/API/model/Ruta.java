package com.picotrake.API.model;

import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.picotrake.API.converter.JsonNodeConverter;
import com.picotrake.API.enums.DificultadRuta;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rutas")
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ruta;

    private String nombreRuta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DificultadRuta dificultad;

    private String ubicacion;

    @Column(nullable = true)
    private String descripcion;

    @Column(nullable = true)
    private Time duracion;

    @Convert(converter = JsonNodeConverter.class)
    @Column(name = "geojson", columnDefinition = "json")
    private JsonNode geojson;


    @OneToMany(mappedBy = "ruta")
    private List<HistorialActividad> historialActividades;
}
