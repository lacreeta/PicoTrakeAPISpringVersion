package com.picotrake.API.service;

import java.util.List;
import java.util.Optional;

import com.picotrake.API.dto.SuscripcionCreateRequest;
import com.picotrake.API.dto.SuscripcionResponse;
import com.picotrake.API.dto.SuscripcionUpdateRequest;


public interface SuscripcionService {

    List<SuscripcionResponse> getAllSuscriptions();
    Optional<SuscripcionResponse> getSuscriptionById(Long id);
    SuscripcionResponse createSuscription(SuscripcionCreateRequest suscripcion);
    void deleteSuscription(Long id);
    SuscripcionResponse updateSuscription(Long id, SuscripcionUpdateRequest suscripcion);
} 