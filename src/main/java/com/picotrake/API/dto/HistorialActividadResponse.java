package com.picotrake.API.dto;

import java.util.Date;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialActividadResponse {
    private int id_ruta;
    private String nombre_ruta;
    private Date fecha;
}

