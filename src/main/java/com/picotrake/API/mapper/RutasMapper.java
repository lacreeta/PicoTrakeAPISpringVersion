package com.picotrake.API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.picotrake.API.dto.RutaCreate;
import com.picotrake.API.dto.RutaResponse;
import com.picotrake.API.model.Ruta;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = JsonMapper.class)
public interface RutasMapper {
    
    @Mapping(target = "id_ruta", ignore = true)
    @Mapping(source = "geojson", target = "geojson", qualifiedByName = "stringToJsonNode")
    Ruta toEntity(RutaCreate rutaCreate);

    @Mapping(source = "geojson", target = "geojson", qualifiedByName = "jsonNodeToString")
    RutaResponse toResponse(Ruta ruta);
    List<RutaResponse> toResponse(List<Ruta> rutas);
}
