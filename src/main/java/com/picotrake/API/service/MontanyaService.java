package com.picotrake.API.service;

import java.util.List;

import com.picotrake.API.dto.MontanyaResponse;

public interface MontanyaService {

    List<MontanyaResponse> getMountains();
    MontanyaResponse getMountainByName(String name);
    MontanyaResponse createMountain(MontanyaResponse montanyaResponse);
}