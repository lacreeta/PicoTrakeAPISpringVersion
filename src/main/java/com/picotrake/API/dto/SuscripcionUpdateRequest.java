package com.picotrake.API.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuscripcionUpdateRequest {
    private String tipo;
    private float precio;
}