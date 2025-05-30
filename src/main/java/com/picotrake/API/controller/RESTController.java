package com.picotrake.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;
import com.picotrake.API.service.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/picotrake")
public class RESTController {
    
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    public RESTController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @Operation(tags = { "Usuarios" })
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios() {
        List<UsuarioResponse> usuarioResponses = usuarioServiceImpl.getAllUsuarios();
        return ResponseEntity.ok(usuarioResponses);
    }

    @Operation(tags = { "Usuarios" })
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioResponse> usuarioResponse = usuarioServiceImpl.getUsuarioById(id);
        if (usuarioResponse.isPresent()) {
            return ResponseEntity.ok(usuarioResponse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        /*return usuarioServiceImpl.getUsuarioById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
        */
    }

    @Operation(tags = { "Usuarios" })
    @PostMapping("/usuarios")
    public ResponseEntity<String> createUsuario(@RequestBody @Valid UsuarioCreate usuario) {
        usuarioServiceImpl.createUsuario(usuario);
        return ResponseEntity.ok("Usuario creado correctamente.");
    }

    @Operation(tags = { "Usuarios" })
    @DeleteMapping("/usuarios")
    public ResponseEntity<String> deleteUsuario(@RequestBody @Valid UsuarioCreate usuario) {
        usuarioServiceImpl.createUsuario(usuario);
        return ResponseEntity.ok("Usuario creado correctamente.");
    }
}
