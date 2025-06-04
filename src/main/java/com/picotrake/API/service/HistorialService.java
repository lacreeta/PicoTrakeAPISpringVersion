package com.picotrake.API.service;

import java.util.Date;
import java.util.List;

import com.picotrake.API.dto.HistorialActividadRequest;
import com.picotrake.API.dto.HistorialActividadResponse;

public interface HistorialService {
    
    List<HistorialActividadResponse> getHistorial(Long userId);
    List<HistorialActividadResponse> getHistorialByName(Long userId, String nombre_ruta);
    List<HistorialActividadResponse> getHistorialByDate(Long userId, Date fechaInicio, Date fechaFin, String ordenar, int page, int pageSize);
    HistorialActividadResponse createHistorial(HistorialActividadRequest request);
    void deleteHistorial(Long userId);
    void deleteHistorialByName(Long userId, String name);
}
