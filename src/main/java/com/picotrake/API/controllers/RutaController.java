package com.picotrake.API.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picotrake.API.dto.RutaCreate;
import com.picotrake.API.dto.RutaResponse;
import com.picotrake.API.dto.RutaUpdateRequest;
import com.picotrake.API.service.Impl.RutaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class RutaController {
    
    private RutaServiceImpl rutaServiceImpl;

    @Autowired
    public RutaController(RutaServiceImpl rutaServiceImpl) {
        this.rutaServiceImpl = rutaServiceImpl;
    }

    @Operation(tags = { "Rutas" })
    @GetMapping("/rutas")
    public ResponseEntity<List<RutaResponse>> getRoutes() {
        List<RutaResponse> rutaResponses = rutaServiceImpl.getAllRoutes();
        return ResponseEntity.ok(rutaResponses);
    }

    @Operation(tags = { "Rutas" })
    @GetMapping("/rutas/{id}")
    public ResponseEntity<RutaResponse> getRouteById(@PathVariable Long id) {
        return rutaServiceImpl.getRouteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation(tags = { "Rutas" })
    @PostMapping("/rutas")
    public ResponseEntity<String> createRoute(@RequestBody @Valid RutaCreate rutaCreate) {
        rutaServiceImpl.createRoute(rutaCreate);
        return ResponseEntity.ok("Ruta creada correctamente.");
    }

    @Operation(tags = { "Rutas" })
    @GetMapping("/rutas/nombre/{nombre_ruta}")
    public ResponseEntity<RutaResponse> getRouteByName(@PathVariable String nombre_ruta) {
        return ResponseEntity.ok(rutaServiceImpl.getRouteByName(nombre_ruta));
    }

    @Operation(tags = { "Rutas" })
    @PutMapping("/rutas/{nombre_ruta}")
    public ResponseEntity<String> updateRouteByName(@PathVariable String nombre_ruta,
            @RequestBody @Valid RutaUpdateRequest request) {
        rutaServiceImpl.updateRouteByName(nombre_ruta, request);
        return ResponseEntity.ok("Ruta actualizada correctamente.");
    }

    @Operation(tags = { "Rutas" })
    @DeleteMapping("/rutas/{nombre_ruta}")
    public ResponseEntity<String> deleteRouteByName(@PathVariable String nombre_ruta) {
        rutaServiceImpl.deleteRouteByName(nombre_ruta);    
        return ResponseEntity.ok("Ruta eliminada correctamente.");
    }
}
