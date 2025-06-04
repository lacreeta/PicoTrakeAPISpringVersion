package com.picotrake.API.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picotrake.API.dto.SuscripcionCreateRequest;
import com.picotrake.API.dto.SuscripcionResponse;
import com.picotrake.API.dto.SuscripcionUpdateRequest;
import com.picotrake.API.mapper.SuscripcionMapper;
import com.picotrake.API.model.Suscripciones;
import com.picotrake.API.repository.SuscripcionRepository;
import com.picotrake.API.service.SuscripcionService;

@Service
public class SuscripcionServiceImpl implements SuscripcionService {

    private final SuscripcionRepository suscripcionRepository;
    private final SuscripcionMapper suscripcionMapper;

    @Autowired
    public SuscripcionServiceImpl(SuscripcionRepository suscripcionRepository, SuscripcionMapper suscripcionMapper) {
        this.suscripcionRepository = suscripcionRepository;
        this.suscripcionMapper = suscripcionMapper;
    }

    @Override
    public List<SuscripcionResponse> getAllSuscriptions() {
        return suscripcionMapper.toResponse(suscripcionRepository.findAll());
    }

    @Override
    public Optional<SuscripcionResponse> getSuscriptionById(Long id) {
        return suscripcionRepository.findById(id).map(suscripcionMapper::toResponse);
    }

    @Override
    public SuscripcionResponse createSuscription(SuscripcionCreateRequest suscripcionCreate) {
        Suscripciones entidad = suscripcionMapper.toEntity(suscripcionCreate);
        Suscripciones guardada = suscripcionRepository.save(entidad);
        return suscripcionMapper.toResponse(guardada);
    }

    @Override
    public void deleteSuscription(Long id) {
        if (!suscripcionRepository.existsById(id)) {
             throw new RuntimeException("Suscripción no encontrada con ID: " + id);
        }
        suscripcionRepository.deleteById(id);
    }

    @Override
    public SuscripcionResponse updateSuscription(Long id, SuscripcionUpdateRequest suscripcionUpdateRequest) {
        Suscripciones existente = suscripcionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con id: " + id));

        existente.setTipo(suscripcionUpdateRequest.getTipo());
        existente.setPrecio(suscripcionUpdateRequest.getPrecio());

        Suscripciones actualizada = suscripcionRepository.save(existente);
        return suscripcionMapper.toResponse(actualizada);
    }

}
