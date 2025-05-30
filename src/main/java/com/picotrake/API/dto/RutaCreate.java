package com.picotrake.API.dto;

import com.picotrake.API.enums.DificultadRuta;

import lombok.*;

@Data
public class RutaCreate {
    private String nombreRuta;
    private DificultadRuta dificultad;
    private String ubicacion;
    private String descripcion;
    private String geojson;
}
