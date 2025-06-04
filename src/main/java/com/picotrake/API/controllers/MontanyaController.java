package com.picotrake.API.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picotrake.API.dto.MontanyaResponse;
import com.picotrake.API.service.Impl.MontanyaServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class MontanyaController {
    
    private MontanyaServiceImpl montanyaServiceImpl;

    @Autowired
    public MontanyaController(MontanyaServiceImpl montanyaServiceImpl) {
        this.montanyaServiceImpl = montanyaServiceImpl;
    }

    @Operation(tags = { "Montañas" })
    @GetMapping("/mountains")
    public ResponseEntity<List<MontanyaResponse>> getMountains() {
        return ResponseEntity.ok(montanyaServiceImpl.getMountains());
    }

    @Operation(tags = { "Montañas" })
    @GetMapping("/mountains/{nombre_montanya}")
    public ResponseEntity<MontanyaResponse> getMountainByName(@PathVariable String nombre_montanya) {
        return ResponseEntity.ok(montanyaServiceImpl.getMountainByName(nombre_montanya));
    }
    
    @Operation(tags = { "Montañas" })
    @PostMapping("/mountains")
    public ResponseEntity<String> createMountain(@RequestBody @Valid MontanyaResponse montanyaResponse) {
        montanyaServiceImpl.createMountain(montanyaResponse);
        return ResponseEntity.ok("Montaña creada correctamente.");
    }
}
