package com.picotrake.API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;
import com.picotrake.API.model.Usuario;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    
    @Mapping(target = "id_usuarios", ignore = true)
    @Mapping(target = "fecha_registro", expression = "java(new java.util.Date())")
    @Mapping(target = "fecha_inicio_suscripcion", expression = "java(new java.util.Date())")
    @Mapping(target = "fecha_final_suscripcion", ignore = true)
    @Mapping(target = "suscripcion", ignore = true) 
    @Mapping(target = "historialActividades", ignore = true)
    Usuario toEntity(UsuarioCreate dto);

    @Mapping(source = "fecha_registro", target = "fechaRegistro")
    @Mapping(source = "suscripcion.id_suscripciones", target = "idSuscripciones")
    UsuarioResponse toResponse(Usuario usuario);
    List<UsuarioResponse> toResponse(List<Usuario> usuarios);
}
