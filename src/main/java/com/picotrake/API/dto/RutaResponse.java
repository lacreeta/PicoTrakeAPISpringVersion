package com.picotrake.API.dto;

import com.picotrake.API.enums.DificultadRuta;

import lombok.*;

@Data
public class RutaResponse {
    private int idRuta;
    private String nombreRuta;
    private DificultadRuta dificultad;
    private String ubicacion;
    private String descripcion;
    private String geojson;
}
