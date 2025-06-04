package com.picotrake.API.dto;

import java.sql.Time;

import com.picotrake.API.enums.DificultadRuta;

import lombok.*;

@Data
public class RutaUpdateRequest {
    private String nombreRuta;
    private DificultadRuta dificultad;
    private String ubicacion;
    private String descripcion;
    private String geojson;
    private Time duracion; 
}
