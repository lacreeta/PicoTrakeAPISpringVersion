package com.picotrake.API.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateUserRequest {

    private String nombre;
    private String apellido;
    
    @Email(message = "Debe ser un correo electrónico válido")
    private String email;
}