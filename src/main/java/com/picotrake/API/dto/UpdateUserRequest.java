package com.picotrake.API.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateUserRequest {

    private String nombre;
    private String apellido;
    
    @Email(message = "Debe ser un correo electrónico válido")
    @Pattern(
    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
    message = "Formato de email no válido"
    )
    private String email;
}