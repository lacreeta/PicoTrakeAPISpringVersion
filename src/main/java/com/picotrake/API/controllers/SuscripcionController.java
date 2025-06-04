package com.picotrake.API.controllers;

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

import com.picotrake.API.dto.SuscripcionCreateRequest;
import com.picotrake.API.dto.SuscripcionResponse;
import com.picotrake.API.dto.SuscripcionUpdateRequest;
import com.picotrake.API.service.Impl.SuscripcionServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class SuscripcionController {
    
    private SuscripcionServiceImpl suscripcionServiceImpl;

    @Autowired
    public SuscripcionController(SuscripcionServiceImpl suscripcionServiceImpl) {
        this.suscripcionServiceImpl = suscripcionServiceImpl;
    }

    @Operation(tags = { "Suscripciones" })
    @GetMapping("/suscripciones")
    public ResponseEntity<List<SuscripcionResponse>> getAllSuscriptions() {
        List<SuscripcionResponse> suscripcionResponses = suscripcionServiceImpl.getAllSuscriptions();
        return ResponseEntity.ok(suscripcionResponses);
    }

    @Operation(tags = { "Suscripciones" })
    @GetMapping("/suscripciones/{id}")
    public ResponseEntity<SuscripcionResponse> getSuscriptionById(@PathVariable Long id) {
        Optional<SuscripcionResponse> suscripcionResponse = suscripcionServiceImpl.getSuscriptionById(id);
        if (suscripcionResponse.isPresent()) {
            return ResponseEntity.ok(suscripcionResponse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(tags = { "Suscripciones" })
    @PutMapping("/suscripciones/{id}")
    public ResponseEntity<SuscripcionResponse> updateSuscripcion(@PathVariable Long id, @RequestBody @Valid SuscripcionUpdateRequest request) {
        SuscripcionResponse response = suscripcionServiceImpl.updateSuscription(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = { "Suscripciones" })
    @PostMapping("/suscripciones")
    public ResponseEntity<String> createSuscription(@RequestBody @Valid SuscripcionCreateRequest suscripcion) {
        suscripcionServiceImpl.createSuscription(suscripcion);
        return ResponseEntity.ok("Suscripción creada correctamente.");
    }

    @Operation(tags = { "Suscripciones" })
    @DeleteMapping("/suscripciones/{id}")
    public ResponseEntity<String> deleteSuscription(@PathVariable Long id ) {
        suscripcionServiceImpl.deleteSuscription(id);
        return ResponseEntity.ok("Suscripción eliminada correctamente.");
    }
}