package com.picotrake.API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.picotrake.API.dto.HistorialActividadRequest;
import com.picotrake.API.dto.HistorialActividadResponse;
import com.picotrake.API.model.HistorialActividad;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistorialMapper {
    
    HistorialActividad toEntity(HistorialActividadRequest historialActividadRequest);

    HistorialActividadResponse toResponse(HistorialActividad historialActividad);
    List<HistorialActividadResponse> toResponse(List<HistorialActividad> historialActividad);
}
