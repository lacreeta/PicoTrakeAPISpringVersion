package com.picotrake.API.dto;

import com.picotrake.API.enums.DificultadMontanya;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MontanyaResponse {

    private String nombreMontanya;
    private String descripcion;
    private DificultadMontanya dificultad;
    private Boolean acampar;
    private Boolean pernoctar;
    private Boolean especiesPeligrosas;
    private String geojson;
}
