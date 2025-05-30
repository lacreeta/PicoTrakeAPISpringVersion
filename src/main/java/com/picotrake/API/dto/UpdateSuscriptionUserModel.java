package com.picotrake.API.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSuscriptionUserModel {

    @NotNull(message = "El ID de la suscripción es obligatorio")
    private Integer idSuscripcion;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración mínima es 1 mes")
    @Max(value = 12, message = "La duración máxima es 12 meses")
    private Integer duracion;
}
