package com.picotrake.API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.picotrake.API.dto.SuscripcionCreateRequest;
import com.picotrake.API.dto.SuscripcionResponse;
import com.picotrake.API.model.Suscripciones;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SuscripcionMapper {

    @Mapping(target = "id_suscripciones", ignore = true)
    Suscripciones toEntity(SuscripcionCreateRequest suscripcionCreateRequest);

    SuscripcionResponse toResponse(Suscripciones suscripcion);
    List<SuscripcionResponse> toResponse(List<Suscripciones> suscripciones);
} 