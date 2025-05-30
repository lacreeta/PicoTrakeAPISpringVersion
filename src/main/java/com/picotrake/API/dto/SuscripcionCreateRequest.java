package com.picotrake.API.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionCreateRequest {
    private String tipo;
    private float precio;
}