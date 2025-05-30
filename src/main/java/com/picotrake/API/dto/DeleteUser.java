package com.picotrake.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUser {
    
    @NotBlank(message = "La contraseña es obligatoria.")
    private String contrasena;
}
