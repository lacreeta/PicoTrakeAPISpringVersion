package com.picotrake.API.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionResponse {
    private int id_suscripciones;
    private String tipo;
    private float precio;
}
