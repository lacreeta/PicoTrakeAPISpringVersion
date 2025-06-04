package com.picotrake.API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.picotrake.API.dto.MontanyaResponse;
import com.picotrake.API.model.Montanyas;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MontanyaMapper {
    
    MontanyaResponse toResponse(Montanyas montanyas);
    List<MontanyaResponse> toResponse(List<Montanyas> montanyas);

    Montanyas toEntity(MontanyaResponse montanyaResponse);
}