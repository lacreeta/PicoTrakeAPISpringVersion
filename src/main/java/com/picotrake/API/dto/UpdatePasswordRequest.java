package com.picotrake.API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {

    @Pattern(
        regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])[^\\s]{8,}$",
        message = "Formato de contraseña no válido"
    )
    @NotBlank(message = "La contraseña actual es obligatoria.")
    private String contrasenaActual;

    @Pattern(
        regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])[^\\s]{8,}$",
        message = "Formato de contraseña no válido"
    )
    @NotBlank(message = "La nueva contraseña es obligatoria.")
    private String nuevaContrasena;
}