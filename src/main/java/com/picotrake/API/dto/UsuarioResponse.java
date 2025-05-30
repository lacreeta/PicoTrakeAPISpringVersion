package com.picotrake.API.dto;

import java.util.Date;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaRegistro;
    private int idSuscripciones;
}
