package com.picotrake.API.service;

import java.util.List;
import java.util.Optional;


import com.picotrake.API.dto.RutaCreate;
import com.picotrake.API.dto.RutaResponse;
import com.picotrake.API.dto.RutaUpdateRequest;

public interface RutaService {
    
    List<RutaResponse> getAllRoutes();
    Optional<RutaResponse> getRouteById(Long id);
    RutaResponse createRoute(RutaCreate ruta);
    RutaResponse getRouteByName(String name);
    void updateRouteByName(String name, RutaUpdateRequest request);
    void deleteRouteByName(String name);
}