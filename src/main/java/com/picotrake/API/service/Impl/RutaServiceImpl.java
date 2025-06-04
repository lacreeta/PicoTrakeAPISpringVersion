package com.picotrake.API.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.picotrake.API.dto.RutaCreate;
import com.picotrake.API.dto.RutaResponse;
import com.picotrake.API.dto.RutaUpdateRequest;
import com.picotrake.API.mapper.RutasMapper;
import com.picotrake.API.model.Ruta;
import com.picotrake.API.repository.RutasRepository;
import com.picotrake.API.service.RutaService;


@Service
public class RutaServiceImpl implements RutaService {

    private final RutasRepository rutasRepository;
    private final RutasMapper rutasMapper;

    @Autowired
    public RutaServiceImpl(RutasRepository rutasRepository, RutasMapper rutasMapper) {
        this.rutasRepository = rutasRepository;
        this.rutasMapper = rutasMapper;
    }

    @Override
    public List<RutaResponse> getAllRoutes() {
        return rutasMapper.toResponse(rutasRepository.findAll());
    }

    @Override
    public Optional<RutaResponse> getRouteById(Long id) {
        return rutasRepository.findById(id).map(rutasMapper::toResponse);
    }

    @Override
    public RutaResponse createRoute(RutaCreate rutaCreate) {
        Ruta ruta = rutasMapper.toEntity(rutaCreate);
        Ruta rutaGuardada = rutasRepository.save(ruta);
        return rutasMapper.toResponse(rutaGuardada);
    }

    @Override
    public RutaResponse getRouteByName(String name) {
        Ruta ruta = rutasRepository.findByNombreRuta(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        return rutasMapper.toResponse(ruta);
    }

    @Override
    public void updateRouteByName(String name, RutaUpdateRequest request) {
        Ruta ruta = rutasRepository.findByNombreRuta(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
         if (request.getDescripcion() != null) {
            ruta.setDescripcion(request.getDescripcion());
        }
        if (request.getUbicacion() != null) {
            ruta.setUbicacion(request.getUbicacion());
        }
        if (request.getDificultad() != null) {
            ruta.setDificultad(request.getDificultad());
        }
        rutasRepository.save(ruta);
    }

    @Override
    public void deleteRouteByName(String name) {
        Ruta ruta = rutasRepository.findByNombreRuta(name)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        rutasRepository.delete(ruta);
    }
    
}
