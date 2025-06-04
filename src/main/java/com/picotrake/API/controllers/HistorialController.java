package com.picotrake.API.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picotrake.API.auth.JWTUtil;
import com.picotrake.API.dto.HistorialActividadRequest;
import com.picotrake.API.dto.HistorialActividadResponse;
import com.picotrake.API.service.Impl.HistorialServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/")
public class HistorialController {
    
    private HistorialServiceImpl historialServiceImpl;
    private JWTUtil jwtUtil;

    @Autowired
    public HistorialController(HistorialServiceImpl historialServiceImpl, JWTUtil jwtUtil) {
        this.historialServiceImpl = historialServiceImpl;
        this.jwtUtil = jwtUtil;
    }

    @Operation(tags = { "Historial" })
    @GetMapping("/historial/usuario/mis-actividades")
    public ResponseEntity<List<HistorialActividadResponse>> getHistorial(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        return ResponseEntity.ok(historialServiceImpl.getHistorial(userId));
    }

    @Operation(tags = { "Historial" })
    @GetMapping("/historial/usuario/{nombre_ruta}")
    public ResponseEntity<List<HistorialActividadResponse>> getHistorialByRoute(HttpServletRequest request,
            @PathVariable String nombre_ruta) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        return ResponseEntity.ok(historialServiceImpl.getHistorialByName(userId, nombre_ruta));
    }
    
    @Operation(tags = { "Historial" })
    @GetMapping("/historial/usuario/filtrar")
    public ResponseEntity<List<HistorialActividadResponse>> getHistorialByDate(
        HttpServletRequest request,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin,
        @RequestParam(defaultValue = "desc") String ordenar,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int pageSize
    ) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        return ResponseEntity.ok(historialServiceImpl.getHistorialByDate(
                userId, fechaInicio, fechaFin, ordenar, page, pageSize));
    }

    @Operation(tags = { "Historial" })
    @PostMapping("/historial")
    public ResponseEntity<String> createHistorial(@RequestBody @Valid HistorialActividadRequest request) {
        historialServiceImpl.createHistorial(request);
        return ResponseEntity.ok("Historial creado correctamente.");
    }

    @Operation(tags = { "Historial" })
    @DeleteMapping("/usuario/historial")
    public ResponseEntity<String> deleteAllHistorial(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        historialServiceImpl.deleteHistorial(userId);
        return ResponseEntity.ok("Historial eliminado correctamente.");
    }

    @Operation(tags = { "Historial" })
    @DeleteMapping("/usuario/historial/{nombre_ruta}")
    public ResponseEntity<String> deleteHistorialByRoute(HttpServletRequest request, @PathVariable String nombre_ruta) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        historialServiceImpl.deleteHistorialByName(userId, nombre_ruta);
        return ResponseEntity.ok("Historial eliminado para " + nombre_ruta + " correctamente.");
     }
}
