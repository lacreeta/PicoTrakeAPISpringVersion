package com.picotrake.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.picotrake.API.auth.JWTUtil;
import com.picotrake.API.dto.DeleteUser;
import com.picotrake.API.dto.UpdatePasswordRequest;
import com.picotrake.API.dto.UpdateSuscriptionUserModel;
import com.picotrake.API.dto.UpdateUserRequest;
import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;
import com.picotrake.API.service.Impl.UsuarioServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class UsuarioController {

    private UsuarioServiceImpl usuarioServiceImpl;
    private JWTUtil jwtUtil;

    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl, JWTUtil jwtUtil) {
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.jwtUtil = jwtUtil;
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
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteMyAccount(@RequestBody @Valid DeleteUser deleteUserRequest,
            HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));

        usuarioServiceImpl.deleteMyAccount(userId, deleteUserRequest.getContrasena());
        return ResponseEntity.ok("Tu cuenta ha sido eliminada correctamente.");
    }

    @Operation(tags = { "Usuarios" })
    @PutMapping("/usuarios/update/suscription")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> updateSuscriptionUser(
            @RequestBody @Valid UpdateSuscriptionUserModel request,
            HttpServletRequest httpRequest) {

        String authHeader = httpRequest.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));

        usuarioServiceImpl.updateSuscription(userId, request);
        return ResponseEntity.ok("Suscripción actualizada correctamente.");
    }

    @Operation(tags = { "Usuarios" })
    @GetMapping("/usuarios/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> getUsuarioMe(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        return ResponseEntity.ok(usuarioServiceImpl.obtenerNombreUsuario(userId));
    }

    @Operation(tags = { "Usuarios" })
    @GetMapping("/usuarios/meAll")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UsuarioResponse> getUsuarioMeAll(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));
        Optional<UsuarioResponse> usuarioResponse = usuarioServiceImpl.getUsuarioById(userId);
        if (usuarioResponse.isPresent()) {
            return ResponseEntity.ok(usuarioResponse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(tags = { "Usuarios" })
    @PatchMapping("/usuarios/update")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserRequest data, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));

        usuarioServiceImpl.updateUser(userId, data);
        return ResponseEntity.ok("Usuario actualizado correctamente.");

    }

    @Operation(tags = { "Usuarios" })
    @PutMapping("/usuarios/update/password")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> updateUserPassword(@RequestBody @Valid UpdatePasswordRequest data, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        Long userId = Long.parseLong(jwtUtil.extractUserId(token));

        usuarioServiceImpl.updatePassword(userId, data);
        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }
}
