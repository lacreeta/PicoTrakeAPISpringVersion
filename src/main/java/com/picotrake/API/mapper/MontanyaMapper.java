package com.picotrake.API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.picotrake.API.dto.MontanyaResponse;
import com.picotrake.API.model.Montanyas;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MontanyaMapper {

    @Mapping(source = "especies_peligrosas", target = "especiesPeligrosas")
    MontanyaResponse toResponse(Montanyas montanyas);

    @Mapping(source = "especiesPeligrosas", target = "especies_peligrosas")
    Montanyas toEntity(MontanyaResponse montanyaResponse);

    List<MontanyaResponse> toResponse(List<Montanyas> montanyas);
}
