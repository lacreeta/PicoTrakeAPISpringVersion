package com.picotrake.API.service.Impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picotrake.API.dto.HistorialActividadRequest;
import com.picotrake.API.dto.HistorialActividadResponse;
import com.picotrake.API.mapper.HistorialMapper;
import com.picotrake.API.model.HistorialActividad;
import com.picotrake.API.repository.HistorialRepository;
import com.picotrake.API.service.HistorialService;

@Service
public class HistorialServiceImpl implements HistorialService {

    private final HistorialRepository historialRepository;
    private final HistorialMapper historialMapper;

    @Autowired
    public HistorialServiceImpl(HistorialRepository historialRepository, HistorialMapper historialMapper) {
        this.historialRepository = historialRepository;
        this.historialMapper = historialMapper;
    }

    @Override
    public List<HistorialActividadResponse> getHistorial(Long userId) {
        return historialRepository.getHistorialByUsuario(userId);
    }

    @Override
    public List<HistorialActividadResponse> getHistorialByName(Long userId, String nombre_ruta) {
        return historialRepository.getHistorialByNombreRuta(userId, nombre_ruta);
    }

    @Override
    public List<HistorialActividadResponse> getHistorialByDate(Long userId, Date fechaInicio, Date fechaFin, String ordenar, int page, int pageSize) {
        if (fechaInicio == null || fechaFin == null) {
            LocalDate hoy = LocalDate.now();
            fechaFin = java.sql.Date.valueOf(hoy);
            fechaInicio = java.sql.Date.valueOf(hoy.minusDays(30));
        }
        int offset = (page - 1) * pageSize;

        return historialRepository.getHistorialByFecha(
            userId, fechaInicio, fechaFin, pageSize, offset
        );
    }


    @Override
    public HistorialActividadResponse createHistorial(HistorialActividadRequest request) {
        HistorialActividad historial = historialMapper.toEntity(request);
        HistorialActividad guardado = historialRepository.save(historial);
        return historialMapper.toResponse(guardado);
    }

    @Override
    public void deleteHistorial(Long userId) {
        historialRepository.deleteHistorialByUsuario(userId);
    }

    @Override
    public void deleteHistorialByName(Long userId, String name) {
        historialRepository.deleteHistorialByNombreRuta(userId, name);
    }
    
}
