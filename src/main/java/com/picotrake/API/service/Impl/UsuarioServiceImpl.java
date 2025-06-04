package com.picotrake.API.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.picotrake.API.dto.UpdatePasswordRequest;
import com.picotrake.API.dto.UpdateSuscriptionUserModel;
import com.picotrake.API.dto.UpdateUserRequest;
import com.picotrake.API.dto.UsuarioCreate;
import com.picotrake.API.dto.UsuarioResponse;
import com.picotrake.API.mapper.UsuarioMapper;
import com.picotrake.API.model.Suscripciones;
import com.picotrake.API.model.Usuario;
import com.picotrake.API.repository.SuscripcionRepository;
import com.picotrake.API.repository.UsuarioRepository;
import com.picotrake.API.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final SuscripcionRepository suscripcionRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SuscripcionRepository suscripcionRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.suscripcionRepository = suscripcionRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioMapper.toResponse(usuarioRepository.findAll());
    }

    @Override
    public Optional<UsuarioResponse> getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toResponse);
    }

    @Override
    public UsuarioResponse createUsuario(UsuarioCreate usuarioCreate) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreate);
        Suscripciones suscripcion = suscripcionRepository.findById((long)1)
                .orElseThrow(() -> new RuntimeException("Suscripción por defecto no encontrada"));
        usuario.setSuscripcion(suscripcion);    
        String hashedPassword = passwordEncoder.encode(usuarioCreate.getContrasena());
        usuario.setContrasena(hashedPassword);
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    public void deleteMyAccount(Long userId, String rawPassword) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!passwordEncoder.matches(rawPassword, usuario.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        usuarioRepository.deleteById(userId);
    }

    @Override
    public void updateSuscription(Long userId, UpdateSuscriptionUserModel request) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (request.getDuracion() != 1 && request.getDuracion() != 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Duración de suscripción no válida. Solo se permite 1 mes o 12 meses.");
        }

        Suscripciones suscripcion = suscripcionRepository.findById(Long.valueOf(request.getIdSuscripcion()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Suscripción no encontrada"));

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFinal = fechaInicio.plusMonths(request.getDuracion());

        usuario.setSuscripcion(suscripcion);
        usuario.setFecha_inicio_suscripcion(java.sql.Date.valueOf(fechaInicio));
        usuario.setFecha_final_suscripcion(java.sql.Date.valueOf(fechaFinal));

        usuarioRepository.save(usuario);
    }

    public String obtenerNombreUsuario(Long idUsuario) {
        return usuarioRepository.findNombreById(idUsuario);
    }
    
    @Override
    public void updateUser(Long userId, UpdateUserRequest request) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        boolean actualizado = false;

        if (request.getNombre() != null && !request.getNombre().isBlank()) {
            usuario.setNombre(request.getNombre());
            actualizado = true;
        }

        if (request.getApellido() != null && !request.getApellido().isBlank()) {
            usuario.setApellido(request.getApellido());
            actualizado = true;
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            usuario.setEmail(request.getEmail());
            actualizado = true;
        }

        if (!actualizado)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se proporcionaron datos válidos para actualizar.");

        usuarioRepository.save(usuario);
    }
    
    @Override
    public void updatePassword(Long userId, UpdatePasswordRequest request) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getContrasenaActual(), usuario.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "La contraseña actual es incorrecta.");
        }

        if (passwordEncoder.matches(request.getNuevaContrasena(), usuario.getContrasena())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La nueva contraseña no puede ser igual a la actual.");
        }
        
        String hashedPassword = passwordEncoder.encode(request.getNuevaContrasena());
        usuario.setContrasena(hashedPassword);
        usuarioRepository.save(usuario);
    }
}
