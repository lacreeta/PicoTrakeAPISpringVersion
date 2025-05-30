package com.picotrake.API.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = "La contraseña actual es obligatoria.")
    private String contrasenaActual;

    @NotBlank(message = "La nueva contraseña es obligatoria.")
    private String nuevaContrasena;
}