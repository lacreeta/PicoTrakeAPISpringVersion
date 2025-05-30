package com.picotrake.API.dto;

import java.util.Date;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialActividadRequest {
    
    private int id_ruta;
    private Date fecha;
}
