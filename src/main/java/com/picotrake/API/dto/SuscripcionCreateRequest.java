package com.picotrake.API.dto;

import com.picotrake.API.enums.TipoSuscripcion;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionCreateRequest {
    private TipoSuscripcion tipo;
    private float precio;
}