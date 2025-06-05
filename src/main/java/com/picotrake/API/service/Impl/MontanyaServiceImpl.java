package com.picotrake.API.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.picotrake.API.dto.MontanyaResponse;
import com.picotrake.API.mapper.MontanyaMapper;
import com.picotrake.API.model.Montanyas;
import com.picotrake.API.repository.MontanyaRepository;
import com.picotrake.API.service.MontanyaService;

@Service
public class MontanyaServiceImpl implements MontanyaService {

    private final MontanyaRepository montanyaRepository;
    private final MontanyaMapper montanyaMapper;

    @Autowired
    public MontanyaServiceImpl(MontanyaRepository montanyaRepository, MontanyaMapper montanyaMapper) {
        this.montanyaRepository = montanyaRepository;
        this.montanyaMapper = montanyaMapper;
    }

    @Override
    public List<MontanyaResponse> getMountains() {
        return montanyaMapper.toResponse(montanyaRepository.findAll());
    }

    @Override
    public MontanyaResponse getMountainByName(String name) {
        Montanyas montanya = montanyaRepository.findByNombreMontanya(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Montaña no encontrada"));
        return montanyaMapper.toResponse(montanya);
    }

    @Override
    public MontanyaResponse createMountain(MontanyaResponse montanyaResponse) {
        Montanyas montanyas = montanyaMapper.toEntity(montanyaResponse);
        Montanyas guardada = montanyaRepository.save(montanyas);
        return montanyaMapper.toResponse(guardada);
    }

}
